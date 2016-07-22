package org.jigang.generator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wujigang on 16/7/12.
 */
public class MybatisGenerator {
    private static final Map<String, String[]> typeMap = new HashMap<String, String[]>();
    private static final Map<String, String> javaTypeMap = new HashMap<String, String>();

    private String schemaName;
    private String tableName;
    private String packageName;

    static {
        typeMap.put("Integer", new String[]{"int", "tinyint"});
        typeMap.put("BigDecimal", new String[]{"decimal"});
        typeMap.put("Date", new String[]{"date", "datetime"});
        typeMap.put("String", new String[]{"varchar", "char"});

        javaTypeMap.put("Date", "java.util.Date");
        javaTypeMap.put("BigDecimal", "java.math.BigDecimal");

    }
    public void generator() throws IOException {
        packageName = PropertiesHelper.getProps("generator.package");
        String generatorTableStr = PropertiesHelper.getProps("generator.table");
        if (null == generatorTableStr || "".equals(generatorTableStr)) {
            System.out.println("没有要生成的对象");
            return;
        }
        String[] tables = generatorTableStr.split(";");
        if (null == tables || "".equals(tables)) {
            System.out.println("没有要生成的对象");
            return;
        }
        for (String table : tables) {
            if (table != null && !"".equals(table)) {
                String[] str = table.split("\\.");
                schemaName = str[0];
                tableName = str[1];
                //生成表
                generateTable();
            }
        }
    }

    private void generateTable() throws IOException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>开始生成 " + schemaName + "." + tableName + " <<<<<<<<<<<<<<<<<<<<<<<<");
        List<ColumnInfo> columns = DBHelper.getAllColumns(schemaName, tableName);

        if (null != columns && columns.size() > 0) {
            //生成实体类
            String entityName = generateEntity(columns);

            //生成dao
            String daoName = generateDao(entityName);

            //生成mapper
            generateMapper(entityName, daoName, columns);
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>生成 " + schemaName + "." + tableName + " 结束<<<<<<<<<<<<<<<<<<<<<<<<");

    }

    /**
     * 生成实体对象
     * @param columns
     * @return
     */
    private String generateEntity(List<ColumnInfo> columns) throws IOException {
        String entityName = transferJavaName(tableName, true);

        String projectPath = getProjectPath();

        String entityFilePath = projectPath + "/src/main/java/" + packageName.replaceAll("\\.", "/") + "/entity/";
        String entityFile = entityFilePath + entityName + ".java";

        File pathDir = new File(entityFilePath);
        if (!pathDir.exists()) {
            pathDir.mkdirs();
        }

        String content = generateJavaContent(entityName, columns);

        //写入java文件
        writeFile(entityFile, content, "UTF-8");

        return entityName;
    }

    /**
     * 生成java文件内容
     * @param entityName
     * @param columns
     * @return
     */
    private String generateJavaContent(String entityName, List<ColumnInfo> columns) {
        //package 和 import 部分
        StringBuilder top = new StringBuilder();
        top.append("/*\n")
                .append(" * Generated by MybatisGenerator on ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n")
                .append("*/\n");
        top.append("package " + packageName + ".entity;").append("\n\n");

        //类主体
        StringBuilder main = new StringBuilder();
        main.append("/** \n");
        main.append(" * entity for table ").append(schemaName).append(".").append(tableName).append("\n");
        main.append(" */\n");
        main.append("public class ").append(entityName).append(" {\n");

        StringBuilder fields = new StringBuilder();
        StringBuilder getSetters = new StringBuilder();
        StringBuilder toStrings = new StringBuilder();

        toStrings.append("\tpublic String toString() {\n");
        toStrings.append("\t\treturn ");

        List<String> importTypes = new ArrayList<String>();

        if (null != columns && columns.size() > 0) {
            for (int i = 0; i < columns.size(); i++) {
                ColumnInfo column = columns.get(i);

                //field 部分
                String fieldType = getFieldType(column.getType());
                String fieldName = transferJavaName(column.getName(), false);
                fields.append("\tprivate ").append(fieldType).append(" ").append(fieldName).append(";\n\n");
                if (javaTypeMap.containsKey(fieldType) && !importTypes.contains(fieldType)) {
                    top.append("import ").append(javaTypeMap.get(fieldType)).append(";\n");
                    importTypes.add(fieldType);
                }


                //getter,setter部分
                getSetters.append("\tpublic ").append(fieldType).append(" get").append(transferJavaName(column.getName(), true)).append("() {\n");
                getSetters.append("\t\treturn this.").append(fieldName).append(";\n");
                getSetters.append("\t}\n\n");
                getSetters.append("\tpublic void set").append(transferJavaName(column.getName(), true)).append("(").append(fieldType).append(" ").append(fieldName).append(") {\n");
                getSetters.append("\t\tthis.").append(fieldName).append(" = ").append(fieldName).append(";\n");
                getSetters.append("\t}\n\n");

                //toString部分
                if (i == 0) {
                    toStrings.append("\"").append(fieldName).append(" = \" + this.").append(fieldName).append(" ");
                } else if (i % 2 == 0) {
                    toStrings.append("\n\t\t\t").append("+ \", ").append(fieldName).append(" = \" + this.").append(fieldName).append(" ");
                } else {
                    toStrings.append("+ \", ").append(fieldName).append(" = \" + this.").append(fieldName).append("");
                }
            }
        }

        toStrings.append(";\n\t}\n");

        top.append("\n");
        main.append(fields).append("\n").append(getSetters.toString()).append("\n").append(toStrings.toString());
        main.append("}");

        return top.toString() + main.toString();
    }

    /**
     * 获取java字段类型
     * @param type
     * @return
     */
    private String getFieldType(String type) {
        for (Map.Entry<String, String[]> entry : typeMap.entrySet()) {
            if (isContain(entry.getValue(), type)) {
                return entry.getKey();
            }
        }
        return "String";
    }

    private boolean isContain(String[] strArr, String str) {
        for (String s : strArr) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void writeFile(String entityFile, String content, String charset) throws IOException {
        File javaFile = new File(entityFile);
        if (!javaFile.exists()) {
            javaFile.createNewFile();
        }

        //写入内容
        BufferedOutputStream br = null;

        try {
            br = new BufferedOutputStream(new FileOutputStream(javaFile));
            br.write(content.getBytes(charset));
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    /**
     * 以"_"字符分隔单词,生成驼峰的java命名
     * @param tableName
     * @return
     */
    private String transferJavaName(String tableName, boolean firstUpper) {
        String[] words = tableName.split("_");

        String name = null;

        for (String word : words) {
            if (name == null) {
                name = firstUpper ? word.substring(0, 1).toUpperCase() + word.substring(1). toLowerCase() : word.toLowerCase();
            } else {
                name = name + word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            }
        }

        return name;
    }

    /**
     * 生成dao
     * @param entityName
     * @return
     */
    private String generateDao(String entityName) throws IOException {
        String daoName = "I" + entityName + "Dao";

        String projectPath = getProjectPath();

        String daoFilePath = projectPath + "/src/main/java/" + packageName.replaceAll("\\.", "/") + "/dao/";
        String daoFile = daoFilePath + daoName + ".java";

        File pathDir = new File(daoFilePath);
        if (!pathDir.exists()) {
            pathDir.mkdirs();
        }

        String content = getDaoContent(daoName, entityName);

        //写入java文件
        writeFile(daoFile, content, "UTF-8");

        return daoName;
    }

    private String getDaoContent(String daoName, String entityName) {
        StringBuilder top = new StringBuilder();
        top.append("package ").append(packageName).append(".dao;\n\n");
        top.append("import org.apache.ibatis.annotations.Param;\n");
        top.append("import ").append(packageName).append(".entity.").append(entityName).append(";\n");

        StringBuilder main = new StringBuilder();
        main.append("public interface ").append(daoName).append(" {\n");
        main.append("\t").append(entityName).append(" queryById(@Param(\"id\") Integer id);\n\n");
        main.append("\tvoid add(").append(entityName).append(" ")
                .append(entityName.substring(0, 1).toLowerCase()).append(entityName.substring(1)).append(");\n\n");
        main.append("\tvoid update(").append(entityName).append(" ")
                .append(entityName.substring(0, 1).toLowerCase()).append(entityName.substring(1)).append(");\n");


        top.append("\n");
        main.append("}");

        return top.toString() + main.toString();
    }

    /**
     * 获取工程路径
     * @return
     */
    private String getProjectPath() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        String path = url.getPath();

        int fromIndex = path.length() - "/test-classes/".length() - 1;
        String projectPath = null;
        //windows
        if (path.indexOf(":") != -1) {
            projectPath = path.substring(1, path.lastIndexOf("/", fromIndex));
        }
        //mac osx等
        else {
            projectPath = path.substring(0, path.lastIndexOf("/", fromIndex));
        }

        return projectPath;
    }

    /**
     * 生成mapper文件
     *
     * @param entityName
     * @param daoName
     * @param columns
     */
    private void generateMapper(String entityName, String daoName, List<ColumnInfo> columns) throws IOException {
        String mapperName = entityName + "Mapper";

        String projectPath = getProjectPath();

        String mapperFilePath = projectPath + "/src/main/resources/sql/";
        String mapperFile = mapperFilePath + mapperName + ".xml";

        File pathDir = new File(mapperFilePath);
        if (!pathDir.exists()) {
            pathDir.mkdirs();
        }

        String content = getMapperContent(entityName, daoName, columns);

        //写入java文件
        writeFile(mapperFile, content, "UTF-8");

    }

    private String getMapperContent(String entityName, String daoName, List<ColumnInfo> columns) {
        StringBuilder main = new StringBuilder();

        main.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
        main.append("<mapper namespace=\"").append(packageName).append(".dao.").append(daoName).append("\">\n");

        //queryAllColumn
        main.append("\t<sql id=\"query").append(entityName).append("AllColumn\">\n");
        main.append("\t\t SELECT ");
        for (int i = 0; i < columns.size(); i++) {
            ColumnInfo column = columns.get(i);
            if (i == 0) {
                main.append(column.getName()).append(" AS ").append(transferJavaName(column.getName(), false)).append(",\n");
            } else if (i == columns.size() - 1) {
                main.append("\t\t\t").append(column.getName().toUpperCase()).append(" AS ").append(transferJavaName(column.getName(), false)).append("\n");
            }else {
                main.append("\t\t\t").append(column.getName().toUpperCase()).append(" AS ").append(transferJavaName(column.getName(), false)).append(",\n");
            }
        }
        main.append("\t\tFROM ").append(schemaName.toUpperCase()).append(".").append(tableName.toUpperCase()).append("\n\t</sql>\n");
        main.append("\n");

        //queryById
        main.append("\t<select id=\"queryById\" resultType=\"").append(packageName).append(".entity.").append(entityName).append("\">\n");
        main.append("\t\t<include refid=\"query").append(entityName).append("AllColumn\"/>\n");
        main.append("\t\tWHERE ID = #{id}\n");
        main.append("\t</select>\n");
        main.append("\n");

        //add And update
        StringBuilder insertCols = new StringBuilder();
        StringBuilder insertFields = new StringBuilder();
        StringBuilder updateCols = new StringBuilder();

        for (int i = 0; i < columns.size(); i++) {
            ColumnInfo column = columns.get(i);

            if ("ID".equals(column.getName().toUpperCase())) {
                continue;
            }

            String colName = column.getName().toUpperCase();
            String fieldName = transferJavaName(column.getName(), false);

            String suffix = ",\n";
            if ("CREATE_TIME".equals(colName)) {

            } else {
                if ("NO".equals(column.getIsNullable())) {
                    insertCols.append("\t\t\t").append(colName).append(suffix);
                    insertFields.append("\t\t\t#{").append(fieldName).append("}").append(suffix);
                } else {
                    insertCols.append("\t\t\t<if test=\"").append(fieldName).append(" != null\">\n");
                    insertCols.append("\t\t\t\t").append(colName).append(suffix);
                    insertCols.append("\t\t\t</if>\n");

                    insertFields.append("\t\t\t<if test=\"").append(fieldName).append(" != null\">\n");
                    insertFields.append("\t\t\t\t#{").append(fieldName).append("}").append(suffix);
                    insertFields.append("\t\t\t</if>\n");
                }
            }

            if ("UPDATE_TIME".equals(colName)) {
                continue;
            }
            updateCols.append("\t\t\t<if test=\"").append(fieldName).append(" != null\">\n");
            updateCols.append("\t\t\t\t").append(colName).append(" = #{").append(fieldName).append("},\n");
            updateCols.append("\t\t\t</if>\n");
        }

        main.append("\t<insert id=\"add\" keyProperty=\"id\" useGeneratedKeys=\"true\" parameterType=\"").append(packageName).append(".entity.").append(entityName).append("\">\n");
        main.append("\t\tINSERT INTO ").append(schemaName.toUpperCase()).append(".").append(tableName.toUpperCase()).append("(\n");
        main.append(insertCols.toString());
        main.append("\t\t\tCREATE_TIME\n\t\t) values (\n");
        main.append(insertFields.toString());
        main.append("\t\t\t#{createTime}\n\t\t)\n");
        main.append("\t</insert>\n");

        //update
        main.append("\t<update id=\"update\" parameterType=\"").append(packageName).append(".entity.").append(entityName).append("\">\n");
        main.append("\t\tUPDATE ").append(schemaName.toUpperCase()).append(".").append(tableName.toUpperCase()).append(" set \n");
        main.append(updateCols.toString());
        main.append("\t\t\tUPDATE_TIME = #{updateTime}\n\t\tWHERE ID = #{id}\n");
        main.append("\t\t</update>");

        main.append("\n</mapper>");
        return main.toString();
    }

}
