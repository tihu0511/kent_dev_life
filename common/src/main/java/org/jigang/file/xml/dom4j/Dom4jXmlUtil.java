package org.jigang.file.xml.dom4j;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * 轻量,快速的操作xml
 * 适用于内容较少的xml操作,需全部加载至内存
 * Created by wujigang on 16/7/6.
 */
public class Dom4jXmlUtil {
    /** 节点表达式字符串分隔符 **/
    private static final String SPLIT_STR = " ";

    /**
     * 解析xml文件的Document对象
     * @param xmlFile
     * @return
     * @throws DocumentException
     */
    public static Document parseFileDocument(File xmlFile) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(xmlFile);
    }

    /**
     * 解析xml文件的Document对象
     * @param xmlFile
     * @return
     * @throws DocumentException
     */
    public static Document parseFileDocument(String xmlFile) throws DocumentException {
        File file = new File(xmlFile);
        return parseFileDocument(file);
    }

    /**
     * 解析xml字符串的Document对象
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Document parseXmlDocument(String xml) throws DocumentException {
        return DocumentHelper.parseText(xml);
    }

    /**
     * 创建Document对象
     * @return
     */
    public static Document createDocument() {
        return DocumentHelper.createDocument();
    }

    /**
     * 查找当前元素下的节点
     * 节点表达式支持类似于CSS定位方式,只能逐层解析,但支持用空格隔开表示查找下一层元素
     * 节点表达式  "#"表示ID值, "."表示class属性值, "[attributeName=attributeValue]"表示属性值,其它情况为节点名称
     *
     * @param curElement 当前元素
     * @param eleExpression 节点表达式  "#"表示ID值, "."表示class属性值, "[attributeName=attributeValue]"表示属性值,其它情况为节点名称
     * @return
     */
    public static Element getElement(Element curElement, String eleExpression) {
        //1. 替换节点表达式中的多个空格为一个空格
        String expression = eleExpression.trim().replaceAll("\\s+", SPLIT_STR);

        //2. 以空格字符拆分字符串,每个空格表示其子元素
        String[] expres = eleExpression.split(SPLIT_STR);

        if (null != expres && expres.length > 0) {
            Element temp = curElement;
            for (String exp : expres) {
                //3. 获取节点, "#"表示ID值, "."表示class属性值, "[attributeName=attributeValue]"表示属性值,其它情况为节点名称
                if (exp.startsWith("#")) {
                    temp = getElementByAttribute(temp, "id", exp.substring(1));
                } else if (exp.startsWith(".")) {
                    temp = getElementByAttribute(temp, "class", exp.substring(1));
                } else if (exp.startsWith("[") && exp.endsWith("]")) {
                    int index = exp.indexOf("=");
                    String attributeName = exp.substring(1, index);
                    String attributeValue = exp.substring(index + 1, exp.length() - 1);
                    temp = getElementByAttribute(temp, attributeName, attributeValue);
                } else {
                    temp = temp.element(exp);
                }
            }
            //如果元素已经改变,说明找到元素,返回
            if (temp != curElement) {
                return temp;
            }
        }
        return null;
    }

    /**
     * 查找当前元素下的节点
     * 节点表达式支持类似于CSS定位方式
     *
     * @param document
     * @param expression
     * @return
     */
    public static Element getElement(Document document, String expression) {
        return getElement(document.getRootElement(), expression);
    }

    /**
     * 根据属性值找子节点
     * @param element
     * @param attributeName
     * @param attributeValue
     * @return
     */
    public static Element getElementByAttribute(Element element, String attributeName, String attributeValue) {
        List<Element> elements = (List<Element>) element.elements();
        if (null != elements && elements.size() > 0) {
            for (Element ele : elements) {
                Attribute attr = ele.attribute(attributeName);
                if (null != attr && attributeValue.equals(attr.getValue())) {
                    return ele;
                }
            }
        }

        return null;
    }

    /**
     * 格式化输出xml
     * @param xml
     * @param charset
     * @return
     */
    public static String prettyPrint(String xml, String charset) throws IOException, DocumentException {
        Document doc = parseXmlDocument(xml);
        StringWriter writer = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(charset);

        XMLWriter xmlwriter = new XMLWriter(writer, format);
        xmlwriter.write(doc);

        return writer.toString();
    }
}
