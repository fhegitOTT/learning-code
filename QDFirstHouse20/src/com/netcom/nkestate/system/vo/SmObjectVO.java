package com.netcom.nkestate.system.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;

/**
 * 菜单对象表
 */
public class SmObjectVO extends AbstractBaseVO {

	private long appId;
	private long objectId;
	private String objectType;
	private String treeId;
	private String code;
	private String name;
	private String icon;
	private String link;
	private long authorityLevel;
	
	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
		this.settings.put("appId", appId);
	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
		this.settings.put("objectId", objectId);
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
		this.settings.put("objectType", objectType);
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
		this.settings.put("treeId", treeId);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		this.settings.put("code", code);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.settings.put("name", name);
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
		this.settings.put("icon", icon);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
		this.settings.put("link", link);
	}

	public long getAuthorityLevel() {
		return authorityLevel;
	}
	
	public void setAuthorityLevel(long authorityLevel) {
		this.authorityLevel = authorityLevel;
		this.settings.put("authorityLevel", authorityLevel);
	}

}
