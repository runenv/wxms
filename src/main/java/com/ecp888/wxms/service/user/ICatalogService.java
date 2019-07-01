/**
 * 栏目及其子项接口
 */
package com.ecp888.wxms.service.user;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.CatalogVO;

/**
 * @author ecp875
 * @since 2014-07-17
 */
@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/catalog")
public interface ICatalogService {
	
	/**
	 * 分页查询栏目信息
	 * @param catalogVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-17
	 */
	public List<CatalogVO> findCatalogList(CatalogVO catalogVO,PageVO pageVO) throws AppException;
	
	/**
	 * 查询单条栏目信息
	 * @param catalogVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-17
	 */
	@GET
	@Path("/catalog/{catalogId}")
	public CatalogVO findCatalog(@PathParam("catalogId")Integer catalogId) throws AppException;
	
	
	/**
	 * 删除栏目信息
	 * @param customerList
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-17
	 */
	public void deleteCatalog(Integer catalogId)  throws AppException;
	
	public void saveCatalog(CatalogVO catalogVO)throws AppException;
	
	
	/**
	 * 查找栏目树
	 * @param catalogId
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/catalogTree/{catalogId}")
	public List<CatalogVO> findCatalogTree(@PathParam("catalogId")String catalogId)throws AppException;
	
	@GET
	@Path("/catalogTreeByParentId/{parentId}")
	public List<CatalogVO> findCatalogTreeByParentId(@PathParam("parentId")String parentId)throws AppException;
	
}
