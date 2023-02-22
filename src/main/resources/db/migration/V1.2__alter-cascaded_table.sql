ALTER TABLE rating_test.subject_entity DROP FOREIGN KEY FKg3lv7dit6tksut4xkwf0u79by;
ALTER TABLE rating_test.subject_entity ADD CONSTRAINT FKg3lv7dit6tksut4xkwf0u79by FOREIGN KEY (class_id) REFERENCES rating_test.class_entity(id) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE rating_test.subject_entity DROP FOREIGN KEY FKg3lv7dit6tksut4xkwf0u79by;
ALTER TABLE rating_test.subject_entity ADD CONSTRAINT FKg3lv7dit6tksut4xkwf0u79by FOREIGN KEY (class_id) REFERENCES rating_test.class_entity(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rating_test.users DROP FOREIGN KEY FKamk5lft24tjtbmb7xmcgflfmv;
ALTER TABLE rating_test.users ADD CONSTRAINT FKamk5lft24tjtbmb7xmcgflfmv FOREIGN KEY (student_class_id) REFERENCES rating_test.class_entity(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rating_test.users DROP FOREIGN KEY FKfi832e3qv89fq376fuh8920y4;
ALTER TABLE rating_test.users ADD CONSTRAINT FKfi832e3qv89fq376fuh8920y4 FOREIGN KEY (department_id) REFERENCES rating_test.department(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rating_test.user_option DROP FOREIGN KEY FK9noyiqmyvpfjs589q2njbeh9h;
ALTER TABLE rating_test.user_option ADD CONSTRAINT FK9noyiqmyvpfjs589q2njbeh9h FOREIGN KEY (feedback_answer_id) REFERENCES rating_test.feedback_answer(id) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE rating_test.feedback_answer DROP FOREIGN KEY FK40w16ugw3bajvn86ur1fpwyil;
ALTER TABLE rating_test.feedback_answer ADD CONSTRAINT FK40w16ugw3bajvn86ur1fpwyil FOREIGN KEY (feedback_list_id) REFERENCES rating_test.feedback_form_list(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rating_test.feedback_questions DROP FOREIGN KEY FKrtptdm07enh9avxuxm2s2f2dp;
ALTER TABLE rating_test.feedback_questions ADD CONSTRAINT FKrtptdm07enh9avxuxm2s2f2dp FOREIGN KEY (feedback_list_id) REFERENCES rating_test.feedback_form_list(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rating_test.user_option DROP FOREIGN KEY FK7ftuwxew0y3ursni69a4q0x24;
ALTER TABLE rating_test.user_option ADD CONSTRAINT FK7ftuwxew0y3ursni69a4q0x24 FOREIGN KEY (feedback_type_id) REFERENCES rating_test.feedback_form_list(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rating_test.user_option DROP FOREIGN KEY FKthuqpi4cvb9n359j9u02cqhvm;
ALTER TABLE rating_test.user_option ADD CONSTRAINT FKthuqpi4cvb9n359j9u02cqhvm FOREIGN KEY (feedback_question_entity_id) REFERENCES rating_test.feedback_questions(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rating_test.subject_entity DROP FOREIGN KEY FKrqykn2ilsh9gt9j8oc6pqr096;
ALTER TABLE rating_test.subject_entity ADD CONSTRAINT FKrqykn2ilsh9gt9j8oc6pqr096 FOREIGN KEY (semester_id) REFERENCES rating_test.semester_entity(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rating_test.user_option DROP FOREIGN KEY FKih3vtlllyl7new3s8fygqn451;
ALTER TABLE rating_test.user_option ADD CONSTRAINT FKih3vtlllyl7new3s8fygqn451 FOREIGN KEY (semester_entity_id) REFERENCES rating_test.semester_entity(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rating_test.user_option DROP FOREIGN KEY FKkqt7ohskstte0r4djmq5rchmu;
ALTER TABLE rating_test.user_option ADD CONSTRAINT FKkqt7ohskstte0r4djmq5rchmu FOREIGN KEY (user_id) REFERENCES rating_test.users(id) ON DELETE CASCADE ON UPDATE CASCADE;
