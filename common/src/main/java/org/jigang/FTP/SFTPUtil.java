package org.jigang.FTP;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Vector;

/**
 * SFTP操作工具类，调用步骤
 * 1.创建连接 connect()
 * 2.操作
 * 3.关闭连接 closeConnection()
 * Created by BF100271 on 2016/6/22.
 */
public class SFTPUtil {
    private static final Logger logger = Logger.getLogger(SFTPUtil.class);
    private static Session session = null;

    /**
     * 连接sftp服务器
     * @param host  主机
     * @param port  端口
     * @param username  用户名
     * @param password    密码
     * @return
     */
    public static SFTPChannel connect(String host, int port, String username, String password) throws JSchException {
        SFTPChannel channel = new SFTPChannel();
        channel.connect(host, port, username, password);
        return channel;
    }
    /**
     * 上传文件
     *
     * @param directory  上传的目录
     * @param uploadFile  要上传的文件
     * @param sftp
     */
    public static void upload(String directory, String uploadFile, ChannelSftp sftp) throws SftpException, FileNotFoundException {
        sftp.cd(directory);
        File file = new File(uploadFile);
        sftp.put(new FileInputStream(file), file.getName());
        logger.info("Upload file " + uploadFile + " to " + directory + " finished.");
    }

    /**
     * 上传文件
     * @param directory 上传的目录
     * @param uploadFile 要上传的文件
     * @param sftp
     * @throws SftpException
     * @throws FileNotFoundException
     */
    public static void upload(String directory, String uploadFile, SFTPChannel sftp) throws SftpException, FileNotFoundException {
        upload(directory, uploadFile, (ChannelSftp)sftp.getChannel());
    }

    /**
     * 下载文件
     *
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile  存在本地的路径，可以是文件或者目录，如果是目录，下载的文件名与SFTP中文件名相同
     * @param sftp
     * @return
     */
    public static String download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) throws SftpException, IOException {
        sftp.cd(directory);
        String filePath = saveFile;
        File file = new File(saveFile);
        if (file.isDirectory()) {
            filePath = saveFile.endsWith(File.separator) ? saveFile + downloadFile : saveFile + File.separator + downloadFile;
            file = new File(filePath);
        }
        FileOutputStream fo =  new FileOutputStream(file);
        sftp.get(downloadFile, fo);
        fo.close();
        logger.info("Download sftp file " + directory + downloadFile + " to " + saveFile + " finished.");
        return filePath;
    }

    /**
     * 下载文件
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径，可以是文件或者目录，如果是目录，下载的文件名与SFTP中文件名相同
     * @param sftp 自行封装的SFTPChannel对象，包含channel和session
     * @return
     * @throws SftpException
     * @throws IOException
     */
    public static String download(String directory, String downloadFile, String saveFile, SFTPChannel sftp) throws SftpException, IOException {
        return download(directory, downloadFile, saveFile, (ChannelSftp)sftp.getChannel());
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile  要删除的文件
     * @param sftp
     */
    public static void delete(String directory, String deleteFile, ChannelSftp sftp) throws SftpException {
        sftp.cd(directory);
        sftp.rm(deleteFile);
        logger.info("Remove file " + directory + deleteFile + " finished.");
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile  要删除的文件
     * @param sftp
     */
    public static void delete(String directory, String deleteFile, SFTPChannel sftp) throws SftpException {
        delete(directory, deleteFile, (ChannelSftp) sftp.getChannel());
    }

    /**
     * 列出目录下的文件
     *
     * @param directory      要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     */
    public static Vector<Object> listFiles(String directory, ChannelSftp sftp)throws SftpException {
        return sftp.ls(directory);
    }

    /**
     * 关闭连接
     * @param sftp
     */
    public static void closeConnection(SFTPChannel sftp) {
        sftp.closeChannel();
    }
}
