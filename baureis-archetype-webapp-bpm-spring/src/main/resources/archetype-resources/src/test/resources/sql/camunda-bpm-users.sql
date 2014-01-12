--------------------------------------------------------------------------------------------------
-- DELETE ALL

-- Memberships
DELETE FROM act_id_membership WHERE USER_ID_ = 'demo';
DELETE FROM act_id_membership WHERE USER_ID_ = 'mary';
DELETE FROM act_id_membership WHERE USER_ID_ = 'peter';
DELETE FROM act_id_membership WHERE USER_ID_ = 'john';

-- Groups
DELETE FROM act_id_group WHERE ID_ = 'accounting';
DELETE FROM act_id_group WHERE ID_ = 'camunda-admin';
DELETE FROM act_id_group WHERE ID_ = 'management';
DELETE FROM act_id_group WHERE ID_ = 'sales';

-- Users
DELETE FROM act_id_user WHERE ID_ = 'demo';
DELETE FROM act_id_user WHERE ID_ = 'john';
DELETE FROM act_id_user WHERE ID_ = 'mary';
DELETE FROM act_id_user WHERE ID_ = 'peter';


--------------------------------------------------------------------------------------------------
-- INSERT ALL

-- Groups
INSERT INTO act_id_group (ID_, REV_, NAME_, TYPE_) VALUES ('accounting', '1', 'Accounting', 'WORKFLOW');
INSERT INTO act_id_group (ID_, REV_, NAME_, TYPE_) VALUES ('camunda-admin', '1', 'camunda BPM Administrators', 'SYSTEM');
INSERT INTO act_id_group (ID_, REV_, NAME_, TYPE_) VALUES ('management', '1', 'Management', 'WORKFLOW');
INSERT INTO act_id_group (ID_, REV_, NAME_, TYPE_) VALUES ('sales', '1', 'Sales', 'WORKFLOW');

-- Users
INSERT INTO act_id_user (ID_, REV_, FIRST_, LAST_, EMAIL_, PWD_, PICTURE_ID_) VALUES ('demo', '1', 'Demo', 'Demo', 'demo@camunda.org', '{SHA}ieSV55Qc+eQOaYDRSha/AjzNTJE=', NULL);
INSERT INTO act_id_user (ID_, REV_, FIRST_, LAST_, EMAIL_, PWD_, PICTURE_ID_) VALUES ('john', '1', 'John', 'Doe', 'john@camunda.org', '{SHA}pR3afH/1C2Hq6gRENx9KapMB5QE=', NULL);
INSERT INTO act_id_user (ID_, REV_, FIRST_, LAST_, EMAIL_, PWD_, PICTURE_ID_) VALUES ('mary', '1', 'Mary', 'Anne', 'mary@camunda.org', '{SHA}VmUzG5uBmsNYFl+MOJcNyMfdtH0=', NULL);
INSERT INTO act_id_user (ID_, REV_, FIRST_, LAST_, EMAIL_, PWD_, PICTURE_ID_) VALUES ('peter', '1', 'Peter', 'Meter', 'peter@camunda.org', '{SHA}S4Nz0Bbyd1JxmDhbpy/aD+tdoBU=', NULL);

-- Memberships
INSERT INTO act_id_membership (USER_ID_, GROUP_ID_) VALUES ('demo', 'accounting');
INSERT INTO act_id_membership (USER_ID_, GROUP_ID_) VALUES ('demo', 'camunda-admin');
INSERT INTO act_id_membership (USER_ID_, GROUP_ID_) VALUES ('demo', 'management');
INSERT INTO act_id_membership (USER_ID_, GROUP_ID_) VALUES ('demo', 'sales');
INSERT INTO act_id_membership (USER_ID_, GROUP_ID_) VALUES ('mary', 'accounting');
INSERT INTO act_id_membership (USER_ID_, GROUP_ID_) VALUES ('peter', 'management');
INSERT INTO act_id_membership (USER_ID_, GROUP_ID_) VALUES ('john', 'sales');

