package me.bitsoul.noa.service;

import me.bitsoul.noa.dao.IdSequenceDAO;
import me.bitsoul.noa.enums.IdSceneEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lxbang
 * @create 2022/12/2 3:30 下午
 */
@Service
public class IdService {

    @Autowired
    private IdSequenceDAO idSequenceDAO;

    /**
     * 获取唯一id
     * @param scene
     * @return
     */
    public Long getId(IdSceneEnum scene){
        return idSequenceDAO.getNextId(scene.getVal());
    }

}
