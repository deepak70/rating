INSERT INTO class_entity (created_at,created_by,updated_at,update_by,version,active,class_name,department_entity_id) VALUES
	 ('2023-02-09 22:50:20',0,'2023-02-09 22:50:20',0,0,1,'NA',2),
	 ('2023-02-09 22:50:20',0,'2023-02-09 22:50:20',0,0,1,'MCA-I',1),
	 ('2023-02-09 22:50:32',0,'2023-02-09 22:50:32',0,0,1,'MBA-I',2),
	 ('2023-02-09 22:50:20',0,'2023-02-09 22:50:20',0,0,1,'MCA-II',1),
	 ('2023-02-09 22:50:20',0,'2023-02-09 22:50:20',0,0,1,'MBA-II',2);
UPDATE class_entity
SET id=0,created_at='2023-02-09 22:50:20', created_by=0, updated_at='2023-02-09 22:50:20', update_by=0, version=0, active=1, class_name='NA',  department_entity_id=2
WHERE class_name='NA';
