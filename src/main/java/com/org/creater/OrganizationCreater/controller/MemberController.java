package com.org.creater.OrganizationCreater.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.creater.OrganizationCreater.config.EmailCfg;
import com.org.creater.OrganizationCreater.entity.Member;
import com.org.creater.OrganizationCreater.entity.MemberStatus;
import com.org.creater.OrganizationCreater.entity.Organization;
import com.org.creater.OrganizationCreater.service.MemberService;
import com.org.creater.OrganizationCreater.service.OrganizationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private EmailCfg emailCfg;
	
	@Autowired
	private OrganizationService organizationService;
	
	public MemberController() {}
	
	//add new member
	@PostMapping("/member/addMember")
	public Member addMember(@RequestBody Member member, @HttpConstraint HttpServletRequest request) throws MessagingException {
		System.out.println("raw member response "+member.getOrganization());
		Member newMember=service.addMember(member);
		if(newMember==null) {
			return null;
		}
		
		Organization org=organizationService.getOrganizationById(newMember.getOrganization().getOrgId());
		
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		mailSender.setHost(emailCfg.getHost());
		mailSender.setPort(emailCfg.getPort());
		mailSender.setUsername(emailCfg.getUsername());
		mailSender.setPassword(emailCfg.getPassword());
		
		
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper mailMessage=new MimeMessageHelper(message);
		
		mailMessage.setFrom(org.getEmail());//organization email
		mailMessage.setTo(newMember.getEmail());
		mailMessage.setSubject("Invitation to join "+org.getOrgName());
		
		String feedbackUrl=  request.getRequestURL().toString().replace("addMember", "accept/");
		feedbackUrl+= newMember.getMid();
		String body= "<p>Dear "+newMember.getName() +" </p> <br> <p>"
				+ "You have invted to join "+ org.getOrgName() 
				+" please confirm "+ "<br>" +"</P>"
				+"<h3>"+"<a href=\""+feedbackUrl+"\">Accept</a>" +"</h3>"
				+"<br>"+"<h3>"+"<a href=\""+feedbackUrl.replace("accept", "reject")+"\">Reject</a>" +"</h3>";
		mailMessage.setText(body,true);
		System.out.println(request.getServletPath()+"\n" + request.getRequestURL());	
		
		mailSender.send(message);
		
		
		return newMember;
	}
	
	//trigger by accept email invitation
	@GetMapping("/member/accept/{mid}")
	public String acceptMemberInvitation(@PathVariable long mid) {
		
		if( this.service.updateStatus(MemberStatus.ACCEPTED, mid)!=null) {
			return "<h3>Greate! you have accepted the invitation. KIT <br><br> <small>thank you</small></h3>";
		}
		return "Somthing went wrong <br> please try again later.";
	}
	
	//trigger by reject email invitation
	@GetMapping("/member/reject/{mid}")
	public String rejectMemberInvitation(@PathVariable long mid) {
		
		if( this.service.updateStatus(MemberStatus.REJECTED, mid)!=null){
			return "<h4>you have  rejected the invitation. feedback is collected <br><br> thank you.</h4>";
		}
		return "<h5>Something went wrong. sorry.</h5>";
		
	}
	//get all members of a org
	@GetMapping("/member/getAllMembers/{oid}")
	public List<Member> getAllMembersByOrganization(@PathVariable long oid){
		return this.service.getAllMembersByOid(oid);
	}

}
