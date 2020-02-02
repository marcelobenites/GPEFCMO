-- INSERT INTO GPEFCMOdb.TBL_USER (classe,username,lastLogin,password) 
--        SELECT 'ADMINISTRADOR','monica', NULL, '$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm'
-- WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_USER WHERE id = 1);

-- INSERÇÃO DE MILITARES
INSERT INTO GPEFCMOdb.TBL_MILITARY (name,surname,isUser,post_grad,war_name,military_identitiy,militaryUnit_id,address_id) 
       SELECT 'marcelo','benites gonçalves', true, 'TENENTE2', 'BENITES',0906252077,1,1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY WHERE name = 'marcelo');
INSERT INTO GPEFCMOdb.TBL_USER (classe,username,lastLogin,password, military_id) 
       SELECT 'SUPERADMIN','benites', NULL, '$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm',LAST_INSERT_ID()
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_USER WHERE username = 'benites');

INSERT INTO GPEFCMOdb.TBL_MILITARY (name,surname,isUser,post_grad,war_name,military_identitiy,militaryUnit_id,address_id) 
       SELECT 'tenente','netto', true, 'TENENTE1', 'NETTO',0906252077,1,1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY WHERE name = 'tenente');
INSERT INTO GPEFCMOdb.TBL_USER (classe,username,lastLogin,password, military_id) 
       SELECT 'ADMIN','netto', NULL, '$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm',LAST_INSERT_ID()
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_USER WHERE username = 'netto');

INSERT INTO GPEFCMOdb.TBL_MILITARY (name,surname,isUser,post_grad,war_name,military_identitiy,militaryUnit_id,address_id) 
       SELECT 'thiago','thiago', true, 'MAJOR', 'THIAGO', 0906,1,2
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY WHERE name = 'thiago');
INSERT INTO GPEFCMOdb.TBL_USER (classe,username,lastLogin,password, military_id) 
       SELECT 'SUPERADMIN','thiago', NULL, '$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm',LAST_INSERT_ID()
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_USER WHERE username = 'thiago');



INSERT INTO GPEFCMOdb.TBL_MILITARY (name,surname,isUser,post_grad,war_name,military_identitiy,militaryUnit_id,address_id) 
       SELECT 'Santos','França', true, 'CORONEL', 'SANTOS FRANÇA',0905,2,1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY WHERE name = 'monica');
INSERT INTO GPEFCMOdb.TBL_USER (classe,username,lastLogin,password, military_id) 
       SELECT 'ADMIN','sfranca', NULL, '$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm',LAST_INSERT_ID()
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_USER WHERE username = 'sfranca');


INSERT INTO GPEFCMOdb.TBL_MILITARY (name,surname,isUser,post_grad,war_name,military_identitiy,militaryUnit_id,address_id) 
       SELECT 'adonis','donis', true, 'CORONEL', 'Adonis',0909,3,2
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY WHERE name = 'adonis');
INSERT INTO GPEFCMOdb.TBL_USER (classe,username,lastLogin,password, military_id) 
       SELECT 'READ_WRITE','rw', NULL, '$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm',LAST_INSERT_ID()
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_USER WHERE username = 'rw');


INSERT INTO GPEFCMOdb.TBL_MILITARY (name,surname,isUser,post_grad,war_name,military_identitiy,militaryUnit_id,address_id) 
       SELECT 'eduardo','moura', true, 'MAJOR', 'Moura',0913,2,1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY WHERE name = 'eduardo');
INSERT INTO GPEFCMOdb.TBL_USER (classe,username,lastLogin,password, military_id) 
       SELECT 'READ_ONLY','ro', NULL, '$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm',LAST_INSERT_ID()
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_USER WHERE username = 'ro');

INSERT INTO GPEFCMOdb.TBL_MILITARY (name,surname,isUser,post_grad,war_name,military_identitiy,militaryUnit_id,address_id) 
       SELECT 'magno','santos', true, 'MAJOR', 'santos',0919,2,1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY WHERE name = 'magno');
INSERT INTO GPEFCMOdb.TBL_USER (classe,username,lastLogin,password, military_id) 
       SELECT 'ADMIN','magno', NULL, '$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm',LAST_INSERT_ID()
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_USER WHERE username = 'magno');



-- INSERÇÃO DE USUARIOS
-- INSERT INTO GPEFCMOdb.TBL_USER (classe,username,lastLogin,password, military_id) 
--        SELECT 'SUPERADMIN','sadm', NULL, '$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm',1
-- WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_USER WHERE username = 'sadm');
-- INSERT INTO GPEFCMOdb.TBL_USER (classe,username,lastLogin,password, military_id) 
--        SELECT 'ADMIN','monica', NULL, '$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm',2
-- WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_USER WHERE username = 'monica');
-- INSERT INTO GPEFCMOdb.TBL_USER (classe,username,lastLogin,password, military_id) 
--        SELECT 'READ_WRITE','rw', NULL, '$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm',3
-- WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_USER WHERE username = 'rw');
-- INSERT INTO GPEFCMOdb.TBL_USER (classe,username,lastLogin,password, military_id) 
--        SELECT 'READ_ONLY','ro', NULL, '$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm',4
-- WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_USER WHERE username = 'ro');
-- INSERÇÃO DE ZONAS
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Campo Grande', 'MS', 'Brasil'
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Campo Grande');

INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Corumbá', 'MS', 'Brasil' 
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Corumbá');
-- CORUMBÁ REGIÃO DO FORTE COIMBRA
INSERT INTO `GPEFCMOdb`.`TBL_MILITARY_UNITY_TBL_ZONE` (`MilitaryUnit_id`, `zones_id`) 
SELECT '11', '2'
WHERE NOT EXISTS (SELECT 1 FROM `GPEFCMOdb`.`TBL_MILITARY_UNITY_TBL_ZONE` WHERE zones_id = '2');



INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Coxim', 'MS', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Coxim');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Dourados', 'MS', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Dourados');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Ponta Porã', 'MS', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Ponta Porã');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Cuiabá', 'MT', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Cuiabá');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Mundo Novo', 'MS', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Mundo Novo');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Forte Coimbra', 'MS', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Forte Coimbra');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Amambai', 'MS', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Amambai');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Bela Vista', 'MS', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Bela Vista');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Caracol', 'MS', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Caracol');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Iguatemi', 'MS', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Iguatemi');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Porto Murtinho', 'MS', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Porto Murtinho');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Cáceres', 'MT', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Cáceres');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Comodoro', 'MT', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Comodoro');
INSERT INTO GPEFCMOdb.TBL_ZONE (county_name, state_name,country_name)
SELECT 'Vila Bela da Santíssima Trindade', 'MT', 'Brasil'  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ZONE WHERE county_name = 'Vila Bela da Santíssima Trindade');

-- INSERÇÃO DE ENDEREÇOS
INSERT INTO GPEFCMOdb.TBL_ADDRESS (street, number, neighborhood, postal_code, zone_id)
SELECT 'Av. Duque de Caxias','1628','vila alba','79100400',1 
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ADDRESS WHERE id = 1);
INSERT INTO GPEFCMOdb.TBL_ADDRESS (street, number, neighborhood, postal_code, zone_id)
SELECT 'end. Forte Coimbra','0000','bairro de Forte Coimbra','7900000',2 
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_ADDRESS WHERE id = 2);



-- INSERÇÃO DE OUTRAS UNIDADES 
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id) 
SELECT 'Comando Militar do Oeste','CMO','CMDMIL','http://intranet.cmo.eb.mil.br/','8904900',1 
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='CMO');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id) 
SELECT 'Centro de Coordenação de Operações','CCOp','CCOP','http://intranet.cmo.eb.mil.br/','8904900',1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='CCOp');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id) 
SELECT '4ª Brigada de Cavaliaria Mecanizada','4ª Bda C Mec','BDACMEC','http://intranet.cmo.eb.mil.br/','8904900',1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='4ª Bda C Mec');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id) 
SELECT '13ª Brigada de Infantaria Motorizada','13ª Bda Inf Mtz','BDAINFMTZ','http://intranet.cmo.eb.mil.br/','8904900',1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='13ª Bda Inf Mtz');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id) 
SELECT '18ª Brigada de Infantaria de Fronteira','18ª Bda Inf Fron','BDAINFFRON','http://intranet.cmo.eb.mil.br/','8904900',1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='18ª Bda Inf Fron');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id) 
SELECT '17º Regimento de Cavalaria Mecanizado','17º R C MEC','RCMEC','http://intranet.cmo.eb.mil.br/','8904900',1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='17º R C MEC');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id) 
SELECT '10º Regimento de Cavalaria Mecanizado','10º R C MEC','RCMEC','http://intranet.cmo.eb.mil.br/','8904900',1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='10º R C MEC');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id) 
SELECT '2º Batalhão de Fronteira','2º B Fron','BFRON','http://intranet.cmo.eb.mil.br/','8904900',1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='2º B Fron');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id) 
SELECT '2º Companhia de Fronteira','2º CIA Fron','CIAFRON','http://intranet.cmo.eb.mil.br/','8904900',1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='2º CIA Fron');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id) 
SELECT '17º Batalhão de Fronteira','17º B Fron','BFRON','http://intranet.cmo.eb.mil.br/','8904900',1
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='17º B Fron');



-- INSERÇÃO DE EFETIVOS
INSERT INTO `GPEFCMOdb`.`TBL_MU_STAFF` (`id`, `adult_relatives_amount`, `children_amount`, `current_cb`, `current_officers`, `current_soldiers`, `current_st_sgt`, `expected_cb`, `expected_officers`, `expected_soldiers`, `expected_st_sgt`)
VALUES ('1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `GPEFCMOdb`.`TBL_MU_STAFF` (`id`, `adult_relatives_amount`, `children_amount`, `current_cb`, `current_officers`, `current_soldiers`, `current_st_sgt`, `expected_cb`, `expected_officers`, `expected_soldiers`, `expected_st_sgt`)
VALUES ('2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `GPEFCMOdb`.`TBL_MU_STAFF` (`id`, `adult_relatives_amount`, `children_amount`, `current_cb`, `current_officers`, `current_soldiers`, `current_st_sgt`, `expected_cb`, `expected_officers`, `expected_soldiers`, `expected_st_sgt`)
VALUES ('3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `GPEFCMOdb`.`TBL_MU_STAFF` (`id`, `adult_relatives_amount`, `children_amount`, `current_cb`, `current_officers`, `current_soldiers`, `current_st_sgt`, `expected_cb`, `expected_officers`, `expected_soldiers`, `expected_st_sgt`)
VALUES ('4', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `GPEFCMOdb`.`TBL_MU_STAFF` (`id`, `adult_relatives_amount`, `children_amount`, `current_cb`, `current_officers`, `current_soldiers`, `current_st_sgt`, `expected_cb`, `expected_officers`, `expected_soldiers`, `expected_st_sgt`)
VALUES ('5', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `GPEFCMOdb`.`TBL_MU_STAFF` (`id`, `adult_relatives_amount`, `children_amount`, `current_cb`, `current_officers`, `current_soldiers`, `current_st_sgt`, `expected_cb`, `expected_officers`, `expected_soldiers`, `expected_st_sgt`)
VALUES ('6', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `GPEFCMOdb`.`TBL_MU_STAFF` (`id`, `adult_relatives_amount`, `children_amount`, `current_cb`, `current_officers`, `current_soldiers`, `current_st_sgt`, `expected_cb`, `expected_officers`, `expected_soldiers`, `expected_st_sgt`)
VALUES ('7', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `GPEFCMOdb`.`TBL_MU_STAFF` (`id`, `adult_relatives_amount`, `children_amount`, `current_cb`, `current_officers`, `current_soldiers`, `current_st_sgt`, `expected_cb`, `expected_officers`, `expected_soldiers`, `expected_st_sgt`)
VALUES ('8', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `GPEFCMOdb`.`TBL_MU_STAFF` (`id`, `adult_relatives_amount`, `children_amount`, `current_cb`, `current_officers`, `current_soldiers`, `current_st_sgt`, `expected_cb`, `expected_officers`, `expected_soldiers`, `expected_st_sgt`)
VALUES ('9', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `GPEFCMOdb`.`TBL_MU_STAFF` (`id`, `adult_relatives_amount`, `children_amount`, `current_cb`, `current_officers`, `current_soldiers`, `current_st_sgt`, `expected_cb`, `expected_officers`, `expected_soldiers`, `expected_st_sgt`)
VALUES ('10', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');


-- CADASTRO DE PEFs
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id,staff_id,MU_history) 
SELECT 'PEF Forte Coimbra','PEF Forte Coimbra','PEF',null,null,2,1,
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec finibus tellus. Mauris pulvinar orci eget mi porta,
 eu posuere metus accumsan. Fusce id tristique orci. Morbi aliquet venenatis augue in porta. Pellentesque diam nibh, commodo
 eget laoreet et, venenatis id risus. Sed eget hendrerit justo. Pellentesque mollis faucibus malesuada. Morbi ultricies odio 
metus, et ultricies lectus malesuada nec. ' 
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='PEF Forte Coimbra');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id,staff_id,MU_history)  
SELECT 'PEF Guaporé','PEF Guaporé','PEF',null,null,2,2 ,
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec finibus tellus. Mauris pulvinar orci eget mi porta,
 eu posuere metus accumsan. Fusce id tristique orci. Morbi aliquet venenatis augue in porta. Pellentesque diam nibh, commodo
 eget laoreet et, venenatis id risus. Sed eget hendrerit justo. Pellentesque mollis faucibus malesuada. Morbi ultricies odio 
metus, et ultricies lectus malesuada nec. ' 
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='PEF Guaporé');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id,staff_id,MU_history)  
SELECT 'PEF Corixa','PEF Corixa','PEF',null,null,2,3,
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec finibus tellus. Mauris pulvinar orci eget mi porta,
 eu posuere metus accumsan. Fusce id tristique orci. Morbi aliquet venenatis augue in porta. Pellentesque diam nibh, commodo
 eget laoreet et, venenatis id risus. Sed eget hendrerit justo. Pellentesque mollis faucibus malesuada. Morbi ultricies odio 
metus, et ultricies lectus malesuada nec. '  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='PEF Corixa');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id,staff_id,MU_history)  
SELECT 'PEF Fortuna','PEF Fortuna','PEF',null,null,2,4,
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec finibus tellus. Mauris pulvinar orci eget mi porta,
 eu posuere metus accumsan. Fusce id tristique orci. Morbi aliquet venenatis augue in porta. Pellentesque diam nibh, commodo
 eget laoreet et, venenatis id risus. Sed eget hendrerit justo. Pellentesque mollis faucibus malesuada. Morbi ultricies odio 
metus, et ultricies lectus malesuada nec. '  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='PEF Fortuna');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id,staff_id,MU_history)  
SELECT 'PEF Palmarito','PEF Palmarito','PEF',null,null,2,5,
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec finibus tellus. Mauris pulvinar orci eget mi porta,
 eu posuere metus accumsan. Fusce id tristique orci. Morbi aliquet venenatis augue in porta. Pellentesque diam nibh, commodo
 eget laoreet et, venenatis id risus. Sed eget hendrerit justo. Pellentesque mollis faucibus malesuada. Morbi ultricies odio 
metus, et ultricies lectus malesuada nec. ' 
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='PEF Palmarito');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id,staff_id,MU_history)  
SELECT 'PEF Porto Índio','PEF Porto Índio','PEF',null,null,2,6,
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec finibus tellus. Mauris pulvinar orci eget mi porta,
 eu posuere metus accumsan. Fusce id tristique orci. Morbi aliquet venenatis augue in porta. Pellentesque diam nibh, commodo
 eget laoreet et, venenatis id risus. Sed eget hendrerit justo. Pellentesque mollis faucibus malesuada. Morbi ultricies odio 
metus, et ultricies lectus malesuada nec. '  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='PEF Porto Índio');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id,staff_id,MU_history)  
SELECT 'PEF Barranco Branco','PEF Barranco Branco','PEF',null,null,2,7,
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec finibus tellus. Mauris pulvinar orci eget mi porta,
 eu posuere metus accumsan. Fusce id tristique orci. Morbi aliquet venenatis augue in porta. Pellentesque diam nibh, commodo
 eget laoreet et, venenatis id risus. Sed eget hendrerit justo. Pellentesque mollis faucibus malesuada. Morbi ultricies odio 
metus, et ultricies lectus malesuada nec. '  
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='PEF Barranco Branco');

-- CADASTRO DE PELOTÕES
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id,staff_id,MU_history)  
SELECT 'PEL Dst Mundo Novo','PEL Dst Mundo Novo','PELD',null,null,2,8,
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec finibus tellus. Mauris pulvinar orci eget mi porta,
 eu posuere metus accumsan. Fusce id tristique orci. Morbi aliquet venenatis augue in porta. Pellentesque diam nibh, commodo
 eget laoreet et, venenatis id risus. Sed eget hendrerit justo. Pellentesque mollis faucibus malesuada. Morbi ultricies odio 
metus, et ultricies lectus malesuada nec. ' 
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='PEL Dst Mundo Novo');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id,staff_id,MU_history)  
SELECT 'ESQ Dst Iguatemi','ESQ Dst Iguatemi','ESQD',null,null,2,9,
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec finibus tellus. Mauris pulvinar orci eget mi porta,
 eu posuere metus accumsan. Fusce id tristique orci. Morbi aliquet venenatis augue in porta. Pellentesque diam nibh, commodo
 eget laoreet et, venenatis id risus. Sed eget hendrerit justo. Pellentesque mollis faucibus malesuada. Morbi ultricies odio 
metus, et ultricies lectus malesuada nec. ' 
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='ESQ Dst Iguatemi');
INSERT INTO GPEFCMOdb.TBL_MILITARY_UNITY(MU_name,MU_initials,MU_type,MU_website, MU_tel,address_id,staff_id,MU_history)  
SELECT 'PEL Dst Caracol','PEL Dst Caracol','PELD',null,null,2,10,
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec finibus tellus. Mauris pulvinar orci eget mi porta,
 eu posuere metus accumsan. Fusce id tristique orci. Morbi aliquet venenatis augue in porta. Pellentesque diam nibh, commodo
 eget laoreet et, venenatis id risus. Sed eget hendrerit justo. Pellentesque mollis faucibus malesuada. Morbi ultricies odio 
metus, et ultricies lectus malesuada nec. ' 
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MILITARY_UNITY WHERE MU_initials ='PEL Dst Caracol');


-- INSERÇÃO DE FRAÇÕES/IMÓVEIS SOB RESPONSABILIDADE DO PEF/ESQ/DST
INSERT INTO GPEFCMOdb.TBL_MU_ADDITIONAL_FRACTION (fraction_name, total_staff, address_id, related_mu_id, fraction_tel) 
SELECT 'CONTATO TESTE', 0, 2, 11, '(67) xxxx-xxxx'
WHERE NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_ADDITIONAL_FRACTION WHERE fraction_name ='CONTATO TESTE');

 
-- INSERÇÃO DE DEPENDÊNCIAS ENTRE UNIDADES 

-------------------------------------CMO----------------------------------------
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'1' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CMO' and mu2.MU_initials = 'CCOp' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id, Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = '4ª Bda C Mec' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = '13ª Bda Inf Mtz' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = '18ª Bda Inf Fron' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = '17º R C MEC' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = '10º R C MEC' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = '2º B Fron' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = '2º CIA Fron' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = '17º B Fron' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = 'PEL Dst Mundo Novo' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = 'ESQ Dst Iguatemi' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = 'PEL Dst Caracol' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = 'PEF Guaporé' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = 'PEF Palmarito' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = 'PEF Corixa' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = 'PEF Fortuna' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = 'PEF Barranco Branco' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = 'PEF Forte Coimbra' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = 'CMO' and mu2.MU_initials = 'PEF Porto Índio' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);


---------------------------------4ª Bda C Mec-----------------------------------
INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '4ª Bda C Mec' and mu2.MU_initials = '17º R C MEC' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '4ª Bda C Mec' and mu2.MU_initials = '10º R C MEC' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);


INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '4ª Bda C Mec' and mu2.MU_initials = 'PEL Dst Mundo Novo' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);


INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '4ª Bda C Mec' and mu2.MU_initials = 'ESQ Dst Iguatemi' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);


INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '4ª Bda C Mec' and mu2.MU_initials = 'PEL Dst Caracol' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);


-----------------------------------17º R C MEC----------------------------------
INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '17º R C MEC' and mu2.MU_initials = 'ESQ Dst Iguatemi' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '17º R C MEC' and mu2.MU_initials = 'PEL Dst Mundo Novo' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

-----------------------------------10º R C MEC----------------------------------
INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '10º R C MEC' and mu2.MU_initials = 'PEL Dst Caracol' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);


---------------------------------13ª Bda Inf Mtz--------------------------------
INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '13ª Bda Inf Mtz' and mu2.MU_initials = '2º B Fron' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '13ª Bda Inf Mtz' and mu2.MU_initials = 'PEF Guaporé' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '13ª Bda Inf Mtz' and mu2.MU_initials = 'PEF Corixa' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '13ª Bda Inf Mtz' and mu2.MU_initials = 'PEF Fortuna' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '13ª Bda Inf Mtz' and mu2.MU_initials = 'PEF Palmarito' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

------------------------------------2º B Fron-----------------------------------
INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '2º B Fron' and mu2.MU_initials = 'PEF Guaporé' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '2º B Fron' and mu2.MU_initials = 'PEF Corixa' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '2º B Fron' and mu2.MU_initials = 'PEF Fortuna' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '2º B Fron' and mu2.MU_initials = 'PEF Palmarito' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);


---------------------------------18ª Bda Inf Fron-------------------------------
INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '18ª Bda Inf Fron' and mu2.MU_initials = '2º CIA Fron' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '18ª Bda Inf Fron' and mu2.MU_initials = '17º B Fron' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '18ª Bda Inf Fron' and mu2.MU_initials = 'PEF Barranco Branco' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '18ª Bda Inf Fron' and mu2.MU_initials = 'PEF Forte Coimbra' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,false  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '18ª Bda Inf Fron' and mu2.MU_initials = 'PEF Porto Índio' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

---------------------------------2º CIA Fron------------------------------------
INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '2º CIA Fron' and mu2.MU_initials = 'PEF Barranco Branco' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

----------------------------------17º B Fron------------------------------------
INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '17º B Fron' and mu2.MU_initials = 'PEF Forte Coimbra' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
SELECT mu1.id, mu2.id,true  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
where mu1.MU_initials = '17º B Fron' and mu2.MU_initials = 'PEF Porto Índio' and 
NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);

-------------------------------------CCOP---------------------------------------
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'1'  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = '4ª Bda C Mec' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'1'  from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = '13ª Bda Inf Mtz' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'1' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = '18ª Bda Inf Fron' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = '17º R C MEC' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = '10º R C MEC' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = '2º B Fron' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = '2º CIA Fron' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = '17º B Fron' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'PEF Forte Coimbra' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'PEF Guaporé' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'PEF Corixa' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'PEF Fortuna' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'PEF Palmarito' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'PEF Porto Índio' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'PEF Barranco Branco' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'PEL Dst Mundo Novo' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'ESQ Dst Iguatemi' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'PEL Dst Caracol' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials,'0' = 'CCOp' and mu2.MU_initials = '2º CIA Fron' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = '17º B Fron' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'PEF Forte Coimbra' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'PEF Porto Índio' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);
-- 
-- 
-- INSERT INTO GPEFCMOdb.TBL_MU_DEPENDENCIES(superiorUnit_id , subordinatedUnit_id , Dep_type) 
-- SELECT mu1.id, mu2.id,'0' from  GPEFCMOdb.TBL_MILITARY_UNITY mu1, GPEFCMOdb.TBL_MILITARY_UNITY mu2 
-- where mu1.MU_initials = 'CCOp' and mu2.MU_initials = 'PEF Barranco Branco' and 
-- NOT EXISTS (SELECT 1 FROM GPEFCMOdb.TBL_MU_DEPENDENCIES WHERE superiorUnit_id = mu1.id and subordinatedUnit_id = mu2.id);