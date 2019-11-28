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
    @Autowired
    private SpecParamMapper paramMapper;

    /**
     * 根据条件查询规格参数
     * @param gid
     * @return
     */
    public List<SpecParam> queryParams(Long gid) {

        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        return this.paramMapper.select(specParam);
    }

    @Transactional
    public void saveSpecGroup(SpecGroup specGroup) {
        this.groupMapper.insertSelective(specGroup);

    }
}
