package com.ecp888.wxms.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExConstants;
import com.ecp888.wxms.utils.exception.FunctionException;
import com.ecp888.wxms.utils.interfaces.AppOperate;
import com.ecp888.wxms.utils.interfaces.AppResource;
import com.ecp888.wxms.dao.user.IDeptDAO;
import com.ecp888.wxms.service.user.IDeptService;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.DeptVO;
/**
 * 部门信息服务类
 * @author ecp875
 * @since 2014-07-07
 */
@Service("deptService")
@AppResource(code="DeptService",desc="部门管理")
public class DeptService implements IDeptService {
	@Autowired
	IDeptDAO deptDAO;
	
	public List<DeptVO> listDept(DeptVO deptVO, PageVO pageVO)
			throws AppException{
		int total = deptDAO.countDept(deptVO);
		pageVO.setRecordTotal(total);
		return deptDAO.listDept(deptVO,pageVO);
	}
	
	public DeptVO getDept(DeptVO deptVO) throws AppException {
		return deptDAO.getDeptById(deptVO);
	}

	public void saveDept(DeptVO deptVO) throws AppException {
		if(null==deptVO.getParentId()){
			deptVO.setParentId(0);
		}
		if(null==deptVO.getDeptId()){//无部门ID，添加部门	
			DeptVO curDept = deptDAO.getDeptByName(deptVO);	
			if (curDept!=null){
				throw new FunctionException(ExConstants.F_USER_1002);
			}
			addDept(deptVO);
		}else{//有部门ID，修改部门信息
			updateDept(deptVO);
		}
	}

	@AppOperate(code="create",desc="添加部门信息")
	public void addDept(DeptVO deptVO) throws AppException {
		deptDAO.addDept(deptVO);
	}
	
	@AppOperate(code="update",desc="更新部门信息")
	public void updateDept(DeptVO deptVO) throws AppException {
		deptDAO.updateDept(deptVO);
	}

	@AppOperate(code="delete",desc="删除部门信息")
	public void deleteDept(DeptVO deptVO) throws AppException {
		deptDAO.deleteDept(deptVO);
	}

	public List<DeptVO> findDeptTreeById(String deptId) throws AppException {

		return deptDAO.findDeptTreeById(deptId);
	}

	

}
