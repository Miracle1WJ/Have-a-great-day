package entity;

import java.io.Serializable;

public class RoleModule implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer roleId;
	private Integer moduleId;
	private String name_1;
	private String name_2;
	private String authorization;
	
	
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getName_1() {
		return name_1;
	}
	public void setName_1(String string) {
		this.name_1 = string;
	}
	public String getName_2() {
		return name_2;
	}
	public void setName_2(String name_2) {
		this.name_2 = name_2;
	}
	
	
	
}
