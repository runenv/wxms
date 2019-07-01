package com.ecp888.wxms.vo.client.bulletin;

import java.util.Date;

import com.ecp888.wxms.utils.json.JsonDateSerializer;
import com.ecp888.wxms.vo.client.BaseResponse;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 公告App 详细响应封装类
 * @author ecp875
 * @since 2014-08-24
 */
public class BulletinDetailResponse extends BaseResponse{

	private Integer bulletinId;//序列
	private String title;//标题
	private String content;//内容
	private String imgUrl;//图片链接
	private String htmlUrl;//h5图片链接
	private String openUrl;//跳转链接地址,direct_flag=1时必填
	private String directFlag;//是否跳转
	private String startDate;//有效开始日期
	private String endDate;//有效截止日期
	private Integer actStatus;//状态,数据字典值
	private Integer classId;//活动类型
	private Integer lotteryId;//跳转页面,采种id
	private Integer bulletinStatus;//公告状态
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
	public Integer getActStatus() {
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
	public Integer getBulletinStatus() {
		return bulletinStatus;
	}
	public void setBulletinStatus(Integer bulletinStatus) {
		this.bulletinStatus = bulletinStatus;
	}
	
}
