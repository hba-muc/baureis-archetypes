--------------------------------------------------------------------------------------------------
-- DELETE ALL

-- Memberships
DELETE FROM act_id_membership WHERE user_id_ = 'demo';
DELETE FROM act_id_membership WHERE user_id_ = 'mary';
DELETE FROM act_id_membership WHERE user_id_ = 'peter';
DELETE FROM act_id_membership WHERE user_id_ = 'john';

-- Groups
DELETE FROM act_id_group WHERE id_ = 'accounting';
DELETE FROM act_id_group WHERE id_ = 'camunda-admin';
DELETE FROM act_id_group WHERE id_ = 'management';
DELETE FROM act_id_group WHERE id_ = 'sales';

-- Users
DELETE FROM act_id_user WHERE id_ = 'demo';
DELETE FROM act_id_user WHERE id_ = 'john';
DELETE FROM act_id_user WHERE id_ = 'mary';
DELETE FROM act_id_user WHERE id_ = 'peter';


--------------------------------------------------------------------------------------------------
-- INSERT ALL

-- Groups
INSERT INTO act_id_group (id_, rev_, name_, type_) VALUES ('accounting', '1', 'Accounting', 'WORKFLOW');
INSERT INTO act_id_group (id_, rev_, name_, type_) VALUES ('camunda-admin', '1', 'camunda BPM Administrators', 'SYSTEM');
INSERT INTO act_id_group (id_, rev_, name_, type_) VALUES ('management', '1', 'Management', 'WORKFLOW');
INSERT INTO act_id_group (id_, rev_, name_, type_) VALUES ('sales', '1', 'Sales', 'WORKFLOW');

-- Users
INSERT INTO act_id_user (id_, rev_, first_, last_, email_, pwd_, picture_id_) VALUES ('demo', '1', 'Demo', 'Demo', 'demo@camunda.org', '{SHA}ieSV55Qc+eQOaYDRSha/AjzNTJE=', NULL);
INSERT INTO act_id_user (id_, rev_, first_, last_, email_, pwd_, picture_id_) VALUES ('john', '1', 'John', 'Doe', 'john@camunda.org', '{SHA}pR3afH/1C2Hq6gRENx9KapMB5QE=', NULL);
INSERT INTO act_id_user (id_, rev_, first_, last_, email_, pwd_, picture_id_) VALUES ('mary', '1', 'Mary', 'Anne', 'mary@camunda.org', '{SHA}VmUzG5uBmsNYFl+MOJcNyMfdtH0=', NULL);
INSERT INTO act_id_user (id_, rev_, first_, last_, email_, pwd_, picture_id_) VALUES ('peter', '1', 'Peter', 'Meter', 'peter@camunda.org', '{SHA}S4Nz0Bbyd1JxmDhbpy/aD+tdoBU=', NULL);

-- Memberships
INSERT INTO act_id_membership (user_id_, group_id_) VALUES ('demo', 'accounting');
INSERT INTO act_id_membership (user_id_, group_id_) VALUES ('demo', 'camunda-admin');
INSERT INTO act_id_membership (user_id_, group_id_) VALUES ('demo', 'management');
INSERT INTO act_id_membership (user_id_, group_id_) VALUES ('demo', 'sales');
INSERT INTO act_id_membership (user_id_, group_id_) VALUES ('mary', 'accounting');
INSERT INTO act_id_membership (user_id_, group_id_) VALUES ('peter', 'management');
INSERT INTO act_id_membership (user_id_, group_id_) VALUES ('john', 'sales');
