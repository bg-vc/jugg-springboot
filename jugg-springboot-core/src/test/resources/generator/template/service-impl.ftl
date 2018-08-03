package ${bizPackage}.service.impl;

import ${bizPackage}.dao.${modelNameUpperCamel}Mapper;
import ${bizPackage}.model.${modelNameUpperCamel};
import ${bizPackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.common.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by ${author} on ${date}.
 */
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

}
