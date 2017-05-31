/*
 * 系统名称: eden 1.0
 * 模块名称: eden.common.email
 * 类 名 称: SimpleEmailSend.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.util.email;

import java.io.File;

import org.apache.commons.mail.MultiPartEmail;

/**
 * 功能说明: <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2014-5-29 <br>
 * 审核人员: <br>
 * 相关文档: <br>
 * 修改记录: <br>
 * 修改日期 修改人员 修改说明 <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class SimpleEmailSend extends AbstractEmailSend {



	public SimpleEmailSend(String mailServer, Integer mailServerPort,
			String mailFrom, String[] mailTo, String[] mailCc,
			String[] mailBcc, String userName, String userPwd, String charset,
			String subject, String content, File[] attachs) {
		super(mailServer, mailServerPort, mailFrom, mailTo, mailCc, mailBcc,
				userName, userPwd, charset, subject, content, attachs);

	}

	public void send() throws Exception {
		try {
			MultiPartEmail email = new MultiPartEmail();
			init(email);
			email.setMsg(content);
			attach(email);
			email.send();
		} catch (org.apache.commons.mail.EmailException e) {
			throw new Exception("发送简单邮件失败", e);
		}

	}

}
