package me.bitsoul.noa.service;

import me.bitsoul.noa.exception.BusinessException;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @author lxbang
 * @create 2022/12/2 4:26 下午
 */
public interface IService<DO,DTO> {

    /**
     * 转换成dto
     * @param entry
     * @return
     */
    default DTO toDTO(DO entry) {
        try {
            if (Objects.isNull(entry)){
                return null;
            }
            Type[] genericInterfaces = getClass().getGenericInterfaces();
            for (Type genericInterface : genericInterfaces) {
                String typeName = genericInterface.getTypeName();
                if (typeName.startsWith(IService.class.getCanonicalName())){
                    ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
                    Type[] typeArguments = parameterizedType.getActualTypeArguments();
                    Class dtoClass = (Class) typeArguments[1];
                    Object dto = dtoClass.newInstance();
                    BeanUtils.copyProperties(entry,dto);
                    return (DTO) dto;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(500,"转换成dto失败！");
        }
    }

}
