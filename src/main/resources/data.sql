INSERT INTO member (id, name) values (1, 'student')
INSERT INTO member (id, name) values (2, 'teacher')
INSERT INTO member (id, name) values (3, 'teacher2')

INSERT INTO subject (id, name, teacher_id) values (1, 'subject 1', 2)
INSERT INTO subject (id, name, teacher_id) values (2, 'subject 1', 3)

INSERT INTO lecture (id, subject_id, date, capacity) values (1, 1, '241001', 30)
INSERT INTO lecture (id, subject_id, date, capacity) values (2, 1, '241002', 30)
INSERT INTO lecture (id, subject_id, date, capacity) values (3, 1, '241003', 30)
INSERT INTO lecture (id, subject_id, date, capacity) values (4, 1, '241004', 30)

INSERT INTO lecture (id, subject_id, date, capacity) values (5, 2, '241001', 30)
INSERT INTO lecture (id, subject_id, date, capacity) values (6, 2, '241002', 30)
INSERT INTO lecture (id, subject_id, date, capacity) values (7, 2, '241003', 30)
INSERT INTO lecture (id, subject_id, date, capacity) values (8, 2, '241004', 30)