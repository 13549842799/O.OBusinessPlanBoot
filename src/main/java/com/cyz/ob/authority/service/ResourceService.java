package com.cyz.ob.authority.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.basic.enumeration.StatusFlag;
import com.cyz.ob.authority.mapper.ResourcesMapper;
import com.cyz.ob.authority.pojo.Resources;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.basic.service.PageServiceTemplate;

@Service
public class ResourceService extends PageServiceTemplate<Resources, PageEntity<Resources>> {
	
	@Autowired
	private ResourcesMapper mapper;
	
	@Override
	public Resources newEntity() {
		
		return new Resources();
	}

	/**
	 * 
	 * @param userId
	 * @param username
	 * @return
	 */
    public List<Resources> getResourcesForUser(Integer userId) {
		
		List<Resources> list = mapper.getModuleResourcesList(userId,Resources.TOPMENU + "," + Resources.SECONDMENU, DeleteFlag.VALID.getCode(),
						StatusFlag.ENABLE.getCode());
		
		return list;
	}
	
	/**
     * 递归获取树形结构的资源tree对象
     * 算法：递归查询，循环编列队列，当遍历的资源是当前的资源的子级资源，
     *       则添加进当前资源的子集列表中。同时开启下一轮递归
     *       当队列不存在对象或者循环的次数大于当前队列的长度时，停止循环。
     *       如果有子集资源需要添加时，重置计数器，避免计数器跳过资源提早结束循环
     */
	public List<Resources> getResourceTree(Queue<Resources> queue, Resources current) {
        List<Resources> childs = new ArrayList<>(5);
	    int count = 0;//计数器
	    Resources node = null;
	    while (!queue.isEmpty() && queue.size() >= count) {
	        count ++;
	        node = queue.poll();//从队列中获取并移除头部对象  
		    if ((current == null && node.getPid() == null) 
		    		|| (current != null && Objects.equals(current.getId(), node.getPid()))) {
		        count = 0;
		    	childs.add(node);
		    	node.setChilds(getResourceTree(queue, node));
			} else {
			  queue.add(node);//如果不是子级资源，则重新回归队列,防止遗漏资源
			}
		  }
		return childs;
	}
	
    public List<Resources> getResourceTree(List<Resources> all, Resources current) {
		
		List<Resources> childs = new ArrayList<>();
		Resources node = null;
		for (int i = 0, len = all.size(); i < len; i++) {
			node = all.get(i);
			if ((current == null && node.getPid() == null)
					|| (current != null && Objects.equals(current.getId(), node.getPid()))) {
				childs.add(node);
				node.setChilds(getResourceTree(all, node));			
			}
		}
		
		return childs;
	}
   
	public boolean state(int id) {		
		return !(mapper.state(id,StatusFlag.ENABLE.getCode(),StatusFlag.DISABLE.getCode()) == 0);
	}

}
