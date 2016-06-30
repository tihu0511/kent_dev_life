package org.jigang.FTP;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * 封装SFTP连接对象，包含channel和session
 * Created by BF100271 on 2016/6/23.
 */
public class SFTPChannel {
    private static final Logger logger = Logger.getLogger(SFTPChannel.class);

    private Channel channel;
    private Session session;

    /**
     * 连接主机
     * @param host
     * @param port
     * @param username
     * @param password
     * @return
     * @throws JSchException
     */
    public void connect(String host, int port, String username, String password) throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(username, host, port);
        session.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        session.setConfig(sshConfig);
        session.connect();
        channel = session.openChannel("sftp");
        channel.connect();
        logger.info("Connected to " + host + ":" + port + " Successful.");
    }

    /**
     * 关闭channel和session
     */
    public void closeChannel() {
        if (null != channel) {
            channel.disconnect();
        }
        if (null != session) {
            session.disconnect();
        }
    }

    public Channel getChannel() {
        return this.channel;
    }
}
