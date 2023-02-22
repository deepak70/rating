INSERT INTO department (created_at,created_by,updated_at,update_by,version,active,department) VALUES
	 ('2023-02-10 22:32:44',0,'2023-02-10 22:32:44',0,0,1,'NA'),
	 ('2023-02-10 22:32:38',0,'2023-02-10 22:32:38',0,0,1,'MCA'),
	 ('2023-02-10 22:32:44',0,'2023-02-10 22:32:44',0,0,1,'MBA');
UPDATE department
SET id=0,created_at='2023-02-10 22:32:44', created_by=0, updated_at='2023-02-10 22:32:44', update_by=0, version=0, active=1, department='NA'
WHERE department='NA';
