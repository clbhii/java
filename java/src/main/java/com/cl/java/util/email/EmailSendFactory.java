/*
 * 系统名称: eden 1.0
 * 模块名称: eden.common.email
 * 类 名 称: EmailSendFactory.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.util.email;

import java.io.File;
import java.net.URL;

/**
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2014-6-17 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class EmailSendFactory {
	/**
	 * 创建hmtl格式的邮件发送器
	 * @param mailServer 邮件服务器地址
	 * @param mailServerPort 邮件服务器端口
	 * @param mailFrom 发件人
	 * @param mailTo  收件人
	 * @param mailCc  抄送人
	 * @param mailBcc 密送人
	 * @param userName 用户名
	 * @param userPwd 用户密码
	 * @param charset 编码
	 * @param subject 邮件主题
	 * @param content 邮件正文（text）
	 * @param attachs 附件
	 * @param dataSourceUrl 资源的源路径
	 */
	public static EmailSend createImageHtmlEmailSend(String mailServer, Integer mailServerPort,
			String mailFrom, String[] mailTo, String[] mailCc,
			String[] mailBcc, String userName, String userPwd, String charset,
			String subject, String content, File[] attachs,URL dataSourceUrl, boolean ssl){
		return new ImageHtmlEmailSend(mailServer, mailServerPort, mailFrom, mailTo, mailCc, mailBcc, userName,
				userPwd, charset, subject, content, attachs,dataSourceUrl, ssl);
	}
	/**
	 * 创建简单的邮件发送器
	 * @param mailServer 邮件服务器地址
	 * @param mailServerPort 邮件服务器端口
	 * @param mailFrom 发件人
	 * @param mailTo  收件人
	 * @param mailCc  抄送人
	 * @param mailBcc 密送人
	 * @param userName 用户名
	 * @param userPwd 用户密码
	 * @param charset 编码
	 * @param subject 邮件主题
	 * @param content 邮件正文（text）
	 * @param attachs 附件
	 */
	public static EmailSend createSimpleEmailSend(String mailServer, Integer mailServerPort,
			String mailFrom, String[] mailTo, String[] mailCc,
			String[] mailBcc, String userName, String userPwd, String charset,
			String subject, String content, File[] attachs, boolean ssl){
		return new SimpleEmailSend(mailServer, mailServerPort, mailFrom, mailTo, mailCc, mailBcc, userName,
				userPwd, charset, subject, content, attachs, ssl);
	}
	
	
}
