package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SpecificationService {
    @Autowired
    private SpecGroupMapper groupMapper;


    @Autowired
    private SpecParamMapper paramMapper;



    /**
     * 根据分类id查询分组
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return this.groupMapper.select(specGroup);
    }

    /**
     * 根据相关参数查询规格参数
     * 根据是否搜索、是否为通用属性等条件过滤
     * @param gid
     * @param cid
     * @param generic
     * @param searching
     * @return
     */
    public List<SpecParam> queryParams(Long gid,Long cid, Boolean generic, Boolean searching) {

        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setGeneric(generic);
        specParam.setSearching(searching);
        return this.paramMapper.select(specParam);
    }

    @Transactional
    public void saveSpecGroup(SpecGroup specGroup) {
        this.groupMapper.insertSelective(specGroup);

    }
    public List<SpecGroup> querySpecsByCid(Long cid) {
        // 查询规格组
        List<SpecGroup> groups = this.queryGroupsByCid(cid);
        groups.forEach(g -> {
            // 查询组内参数
            g.setParams(this.queryParams(g.getId(), null, null, null));
        });
        return groups;
    }
}
