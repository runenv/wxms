package com.ecp888.wxms.vo.bulletin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ecp888.passport.model.MemberPartner;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.vo.BaseVO;
import com.ecp888.wxms.vo.attachment.AttachmentVO;
/**
 * APP公告信息VO
 * @author ecp875
 * @since 2014-08-03
 */
@XmlRootElement(name = "Rows")
@XmlAccessorType(XmlAccessType.FIELD)
public class BulletinVO extends BaseVO{
	
	public static BulletinVO valueOf(String bulletinId) {
		BulletinVO bulletinVO = new BulletinVO();
		bulletinVO.setBulletinId(Integer.valueOf(bulletinId));
		return bulletinVO;
	}

	private static final long serialVersionUID = -5215845325560986907L;
	public static final String ATTACH_MOUDLE = "bulletin";
	public static final String DIRECTION_UP = "1";
	public static final String DIRECTION_DOWN = "-1";
	/**
	 * 1001:等待，新建公告完成添加，未进行发布操作时为等待状态
	 */
	public static final int ACTSTATUS_WAIT_INIT = 1001; 
	/**
	 * 1002:生效中，公告已经发布，而且在有效期内
	 */
	public static final int ACTSTATUS_VALID_RELEASE = 1002;
	/**
	 * 1003:已过期，公告已经发布，但是已过有效期
	 */
	public static final int ACTSTATUS_OUTOFDATE_RELEASE = 1003;
	/**
	 * 1004:未生效，新建公告已经完成添加已经发布，但是未在有效内
	 */
	public static final int ACTSTATUS_INVALID_RELEASE = 1004;
	/**
	 * 1005:已撤消，公告已经发布，但是执行了撤消操作
	 */
	public static final int ACTSTATUS_CANCEL_RELEASE = 1005;
	
	private Map<String, ArrayList<MemberPartner>> partnerMap = new HashMap<String, ArrayList<MemberPartner>>();
	
	private List<String> targetUserList = new ArrayList<String>();
	
	private AttachmentVO attachmentVO = new AttachmentVO();
	
	private AttachmentVO targetUserAttachVO = new AttachmentVO(); //目标用户附件
	

	private Integer bulletinId;//序列
	private String title;//标题
	private String content;//内容
	private String imgUrl;//图片链接
	private String htmlUrl;//h5图片链接
	private String openUrl;//跳转链接地址,direct_flag=1时必填
	private String directFlag="1";//是否跳转
	private String startDate;//有效开始日期
	private String endDate;//有效截止日期
	private Integer orderNum;//排序号
	private Integer actStatus;//状态,数据字典值
	private Integer classId;//活动类型
	private Integer lotteryId;//跳转页面,采种id
	private String testUser;//测试用户
	private String targetUser;//目标用户,附件ID
	private Integer bulletinStatus;//公告状态
	
	private String titleWap;//公告类型,数据字典值
	private String channel;//渠道
	private String platform;//发布平台
	private String className;//活动类型名称
	private String actStatusName;//状态名称,数据字典值
	private String imgUrlPre;//上一个图片链接
	private String htmlUrlPre;//上一个h5图片链接
	
	
	public Map<String, ArrayList<MemberPartner>> getPartnerMap() {
		return this.partnerMap;
	}
	
	public void setPartnerMap(Map<String, ArrayList<MemberPartner>> partnerMap) {
		this.partnerMap = partnerMap;
	}
	
	public Integer getBulletinId() {
		return bulletinId;
	}
	public void setBulletinId(Integer bulletinId) {
		this.bulletinId = bulletinId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getHtmlUrl() {
		return htmlUrl;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}
	public String getOpenUrl() {
		return openUrl;
	}
	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}
	public String getDirectFlag() {
		return directFlag;
	}
	public void setDirectFlag(String directFlag) {
		this.directFlag = directFlag;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getActStatus() {
		if(null == bulletinStatus || !AppUtil.isNotEmpty(this.startDate) || !AppUtil.isNotEmpty(this.endDate)){
			return actStatus;
		}
		String fmt = "yyyy-MM-dd HH:mm:ss";
		long startDate = AppUtil.getDate(this.startDate, fmt).getTime();
		long endDate = AppUtil.getDate(this.endDate, fmt).getTime(); 
		long today = new Date().getTime();
		
		if(this.bulletinStatus == 0){
			//pushStatus为“未发布”状态
			return ACTSTATUS_WAIT_INIT;
		}else if(this.bulletinStatus == 2){
			//pushStatus为“已撤销”状态
			return ACTSTATUS_CANCEL_RELEASE;
		}else{
			//pushStatus为“已发布”状态
			if(startDate > today){
				return ACTSTATUS_INVALID_RELEASE;
			}else if(startDate < today && today > endDate){
				return ACTSTATUS_OUTOFDATE_RELEASE;
			}else if(startDate <= today && today <= endDate){
				return ACTSTATUS_VALID_RELEASE;
			}
		}
		return actStatus;
	}
	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getLotteryId() {
		return lotteryId;
	}
	public void setLotteryId(Integer lotteryId) {
		this.lotteryId = lotteryId;
	}
	public String getTitleWap() {
		return titleWap;
	}
	public void setTitleWap(String titleWap) {
		this.titleWap = titleWap;
	}
	public String getTestUser() {
		return testUser;
	}
	public void setTestUser(String testUser) {
		this.testUser = testUser;
	}

	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}
	
	public String getTargetUser() {
		return targetUser;
	}

	public List<String> getTargetUserList() {
		return targetUserList;
	}

	public void setTargetUserList(List<String> targetUserList) {
		this.targetUserList = targetUserList;
	}

	public AttachmentVO getAttachmentVO() {
		return attachmentVO;
	}

	public void setAttachmentVO(AttachmentVO attachmentVO) {
		this.attachmentVO = attachmentVO;
	}
	
	public AttachmentVO getTargetUserAttachVO() {
		return targetUserAttachVO;
	}

	public void setTargetUserAttachVO(AttachmentVO targetUserAttachVO) {
		this.targetUserAttachVO = targetUserAttachVO;
	}
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getImgUrlPre() {
		return imgUrlPre;
	}

	public void setImgUrlPre(String imgUrlPre) {
		this.imgUrlPre = imgUrlPre;
	}

	public String getHtmlUrlPre() {
		return htmlUrlPre;
	}

	public void setHtmlUrlPre(String htmlUrlPre) {
		this.htmlUrlPre = htmlUrlPre;
	}

	public String getActStatusName() {
		return actStatusName;
	}

	public void setActStatusName(String actStatusName) {
		this.actStatusName = actStatusName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getBulletinStatus() {
		return bulletinStatus;
	}

	public void setBulletinStatus(Integer bulletinStatus) {
		this.bulletinStatus = bulletinStatus;
	}
	
}
