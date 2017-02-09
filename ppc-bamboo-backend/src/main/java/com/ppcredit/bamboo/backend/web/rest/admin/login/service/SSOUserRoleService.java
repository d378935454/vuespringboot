package com.ppcredit.bamboo.backend.web.rest.admin.login.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserRoleDTO;

@Transactional
@Component
public interface SSOUserRoleService extends JpaRepository<SSOUserRoleDTO, String> {
	
//	@Query("FROM SSORoleDTO s WHERE s.name=:name")
//	List<SSORoleDTO> findByName(@Param("name") String name) ;
	
	/*public List<SSOResFuncDTO> list();
	
	public List<SSOResFuncDTO> getUserFuncsList(SSOUserDTO u);

	public List<SSORoleFuncDTO> listRoleFuncs(List<SSORoleDTO> roles);

	public void saveFunc(SSOResFuncDTO f);
	
	public List<SSORoleDTO> listRoles(SSOUserDTO u);
	
	public List<SSORoleDTO> listRoles() ;
	
	
	public void saveRoles(Set<String> all,String name,String remark);
	
	public SSORoleDTO selectRoles(String id) ;
	
	public List<SSORoleFuncDTO> selectRoleFunc(SSORoleDTO role) ;
	
	public void addAdminRoleFunc(SSORoleDTO role);
	
	public SSOResFuncDTO selectMenu(String menuId) ;
	
	public void updateMenu(SSOResFuncDTO dto) ;
	
	public SSOUserRoleDTO selectUserRole(String userId) ;
	
	public List<SSOUserRoleDTO> queryUserList(String roleId,String status,String searchCondition) ;
	
	public List<SSORoleDTO> queryRoleList(String roleName) ;
	
	public void userFreeze(String userId,String status) ;
	
	public void deleteMenu(String menuId) ;
	
	public void resetPass(String userId,String newPass) ;
	
	public void editRole(Set<String> all,String name,String remark,String roleId);
	
	public List<SSOUserRoleDTO> listRoleFuncs() ;
	
	public void saveUserOperationLog(SSOUserOperationLogDTO userOperationLogDTO);
	
	public List<String> listOperationLogType();
	
	public Pager getAPIOperationLogForPager(String start,String end,String operationPerson,String operationType, int offset, int pagesize, String sortCol, String sortDir);
	
	public Pager getAllDataDictionary(int offset, int pagesize);
	
	public List<SSOAPIUserDTO> getAPIUser(SSOUserDTO u);
	
	public List<APIUserDTO> getInSSOUser(SSOUserDTO u);
	
	public List<APIUserDTO> getNotInSSOUser(SSOUserDTO u);*/
}
