package com.ecp888.wxms.service.user;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.user.RoleRightVO;

@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/roleRight")
public interface IRoleRightService {

	@GET
	@Path("/roleRightTree/{roleId}")
	List<RoleRightVO> listRoleRight(@PathParam("roleId")Integer roleId)throws AppException;
	
	@GET
	@Path("/roleRight/{roleId}")
	List<RoleRightVO> listRoleRightByRoleId(@PathParam("roleId")Integer roleId)throws AppException;
	
	@GET
	@Path("/update")
	public void synchAppResource()throws AppException;
	
	boolean hasRoleRight(Map<String, String> map)throws AppException;
	
	@GET
	@Path("/hasPermission/{rightPoint}")
	boolean hasRoleRight(@PathParam("rightPoint")String rightPoint)throws AppException;
}
