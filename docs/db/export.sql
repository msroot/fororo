--------------------------------------------------------
--  File created - Sunday-May-22-2011   
--------------------------------------------------------
  DROP TABLE "S3252905"."FCOMMENT" cascade constraints;
  DROP TABLE "S3252905"."FCONFIG" cascade constraints;
  DROP TABLE "S3252905"."FTHREAD" cascade constraints;
  DROP TABLE "S3252905"."FTOPIC" cascade constraints;
  DROP TABLE "S3252905"."FUSER" cascade constraints;
  DROP SEQUENCE "S3252905"."FCOMMENT_ID_SEQ";
  DROP SEQUENCE "S3252905"."FOROROID";
  DROP SEQUENCE "S3252905"."FTHREAD_ID_SEQ";
  DROP SEQUENCE "S3252905"."FTOPIC_ID_SEQ";
  DROP SEQUENCE "S3252905"."FUSER_ID_SEQ";
  DROP SEQUENCE "S3252905"."TEST1_SEQ";
  DROP VIEW "S3252905"."ACTION_ACTORS";
  DROP VIEW "S3252905"."MON";
--------------------------------------------------------
--  DDL for Sequence FCOMMENT_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "S3252905"."FCOMMENT_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 101 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence FOROROID
--------------------------------------------------------

   CREATE SEQUENCE  "S3252905"."FOROROID"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence FTHREAD_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "S3252905"."FTHREAD_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 381 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence FTOPIC_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "S3252905"."FTOPIC_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 741 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence FUSER_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "S3252905"."FUSER_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 581 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TEST1_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "S3252905"."TEST1_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table FCOMMENT
--------------------------------------------------------

  CREATE TABLE "S3252905"."FCOMMENT" 
   (	"ID" NUMBER, 
	"CONTENT" VARCHAR2(200 BYTE), 
	"THREADID" VARCHAR2(20 BYTE), 
	"USERID" VARCHAR2(50 BYTE), 
	"CREATED" VARCHAR2(100 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table FCONFIG
--------------------------------------------------------

  CREATE TABLE "S3252905"."FCONFIG" 
   (	"ID" VARCHAR2(900 BYTE), 
	"WELCOME" VARCHAR2(900 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table FTHREAD
--------------------------------------------------------

  CREATE TABLE "S3252905"."FTHREAD" 
   (	"ID" NUMBER, 
	"TITLE" VARCHAR2(2000 BYTE), 
	"DESCRIPTION" VARCHAR2(2000 BYTE), 
	"TOPICID" VARCHAR2(2000 BYTE), 
	"USERID" VARCHAR2(20 BYTE), 
	"CREATED" VARCHAR2(100 BYTE), 
	"PARENTID" VARCHAR2(20 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table FTOPIC
--------------------------------------------------------

  CREATE TABLE "S3252905"."FTOPIC" 
   (	"ID" NUMBER, 
	"NAME" VARCHAR2(2000 BYTE), 
	"DESCRIPTION" VARCHAR2(2000 BYTE), 
	"ISACTIVE" VARCHAR2(20 BYTE), 
	"USERID" VARCHAR2(20 BYTE), 
	"CREATED" VARCHAR2(100 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table FUSER
--------------------------------------------------------

  CREATE TABLE "S3252905"."FUSER" 
   (	"ID" NUMBER, 
	"NAME" VARCHAR2(2000 BYTE), 
	"PASSWORD" VARCHAR2(2000 BYTE), 
	"ISACTIVE" VARCHAR2(2000 BYTE), 
	"TYPE" VARCHAR2(20 BYTE), 
	"CREATED" VARCHAR2(100 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;

---------------------------------------------------
--   DATA FOR TABLE FTOPIC
--   FILTER = none used
---------------------------------------------------
REM INSERTING into S3252905.FTOPIC
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (721,'General','Discuss about the this forum','true','admin','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (722,'Blues','From new orleans','true','vic','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (723,'Jazz ','Only for true jazz lovers ','true','edd','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (724,'BossaNova','The sound that seduced the world','true','john','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (725,'Electronica','Dubidubi shake your body','true','john','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (726,'Greek','Oooooopa the bouzouki','true','vic','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (727,'80s','if you ever had funny hair','false','vic','Sun May 22 00:51:03 EST 2011');

---------------------------------------------------
--   END DATA FOR TABLE FTOPIC
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE FCONFIG
--   FILTER = none used
---------------------------------------------------
REM INSERTING into S3252905.FCONFIG
Insert into S3252905.FCONFIG (ID,WELCOME) values ('1','Welcome to fororo forum! hope u have a great time!');

---------------------------------------------------
--   END DATA FOR TABLE FCONFIG
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE FTHREAD
--   FILTER = none used
---------------------------------------------------
REM INSERTING into S3252905.FTHREAD
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (363,'acid jazz? ',' is acid jazz a kind of jazz?','722','admin','Sun May 22 00:56:09 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (364,'origin of Bossanova','where does bossanova come from? ','724','edd','Sun May 22 00:56:28 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (365,'origin of electro','where does the electronica come from?','725','vic','Sun May 22 00:56:59 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (366,'origin of bouzouki','where does the bouzouki come from? ','726','vic','Sun May 22 00:57:28 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (373,'Bossa Nova under the starts','The new album from BigCotes comming soon','724','admin','Sun May 22 01:03:32 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (375,'Mambo Bouzouki ','is there any bambo bouzouki song?','726','admin','Sun May 22 01:04:48 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (377,'Sasha Involver','everyone msut have','725','john','Sun May 22 01:05:37 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (361,'where is bbking?','Anyone knows where is this guy?','721','john','Sun May 22 00:55:33 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (362,'origin of jazz ','where does jazz come from? ','722','edd','Sun May 22 00:55:58 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (370,'About Festivals','What the best festivals in melbourne?','721','vic','Sun May 22 01:00:59 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (371,'What''s the kk bings guitar name?','What''s the kk bings guitar name?','722','admin','Sun May 22 01:02:03 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (367,'How can i learn guitar','Doent anyone  know how to learn guitar?','721','john','Sun May 22 00:59:50 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (369,'About vinyls','how can i clean my vinuls?','721','admin','Sun May 22 01:00:44 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (374,'Bossa Nova radio?','does anyone know?','724','edd','Sun May 22 01:04:02 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (376,' origin of jazz  ','where does jazz come from?','723','edd','Sun May 22 01:05:10 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (368,'What is the best collections?','What is the best collections anyone must have?','721','john','Sun May 22 01:00:19 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (372,'Where is the girl from Ipanema?','Is she married?','724','vic','Sun May 22 01:02:56 EST 2011',null);

---------------------------------------------------
--   END DATA FOR TABLE FTHREAD
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE FUSER
--   FILTER = none used
---------------------------------------------------
REM INSERTING into S3252905.FUSER
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (566,'admin','abcd1234','true','ADMIN','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (567,'edd','abcd1234','true','ADMIN','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (568,'john','abcd1234','true','ADMIN','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (569,'vic','abcd1234','true','NORMAL','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (570,'inactive','abcd1234','false','NORMAL','Sun May 22 00:47:52 EST 2011');

---------------------------------------------------
--   END DATA FOR TABLE FUSER
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE FCOMMENT
--   FILTER = none used
---------------------------------------------------
REM INSERTING into S3252905.FCOMMENT

---------------------------------------------------
--   END DATA FOR TABLE FCOMMENT
---------------------------------------------------
--------------------------------------------------------
--  Constraints for Table FCOMMENT
--------------------------------------------------------

  ALTER TABLE "S3252905"."FCOMMENT" MODIFY ("ID" NOT NULL ENABLE);

--------------------------------------------------------
--  Constraints for Table FTHREAD
--------------------------------------------------------

  ALTER TABLE "S3252905"."FTHREAD" ADD CONSTRAINT "FTHREAD_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "S3252905"."FTHREAD" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FTOPIC
--------------------------------------------------------

  ALTER TABLE "S3252905"."FTOPIC" ADD CONSTRAINT "FTOPIC_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "S3252905"."FTOPIC" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FUSER
--------------------------------------------------------

  ALTER TABLE "S3252905"."FUSER" ADD CONSTRAINT "FUSER_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "S3252905"."FUSER" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  DDL for Index FTHREAD_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "S3252905"."FTHREAD_PK" ON "S3252905"."FTHREAD" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index FTOPIC_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "S3252905"."FTOPIC_PK" ON "S3252905"."FTOPIC" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index FUSER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "S3252905"."FUSER_PK" ON "S3252905"."FUSER" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;





--------------------------------------------------------
--  DDL for Synonymn DUAL
--------------------------------------------------------

  CREATE OR REPLACE PUBLIC SYNONYM "DUAL" FOR "SYS"."DUAL";
--------------------------------------------------------
--  DDL for Trigger FCOMMENT_INSERT
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "S3252905"."FCOMMENT_INSERT" 
before insert on FCOMMENT
for each row
begin
    select FCOMMENT_id_seq.nextval into :new.id from dual;
end;
/
ALTER TRIGGER "S3252905"."FCOMMENT_INSERT" ENABLE;
--------------------------------------------------------
--  DDL for Trigger FTHREAD_INSERT
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "S3252905"."FTHREAD_INSERT" 
before insert on FTHREAD
for each row
begin
    select FTHREAD_id_seq.nextval into :new.id from dual;
end;
/
ALTER TRIGGER "S3252905"."FTHREAD_INSERT" ENABLE;
--------------------------------------------------------
--  DDL for Trigger FTOPIC_INSERT
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "S3252905"."FTOPIC_INSERT" 
before insert on FTOPIC
for each row
begin
    select FTOPIC_id_seq.nextval into :new.id from dual;
end;
/
ALTER TRIGGER "S3252905"."FTOPIC_INSERT" ENABLE;
--------------------------------------------------------
--  DDL for Trigger USER_INSERT
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "S3252905"."USER_INSERT" 
before insert on FUSER
for each row
begin
    select FUSER_id_seq.nextval into :new.id from dual;
end;
/
ALTER TRIGGER "S3252905"."USER_INSERT" ENABLE;
--------------------------------------------------------
--  DDL for View ACTION_ACTORS
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "S3252905"."ACTION_ACTORS" ("ACTORID", "FIRSTNAME", "LASTNAME", "BIRTHPLACE") AS 
  select a.actorid, a.firstname, a.lastname, a.birthplace from actor a, cast c, genre g where a.actorid=c.actorid and c.mvid=g.mvid and g.genre='Action';
--------------------------------------------------------
--  DDL for View MON
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "S3252905"."MON" ("M", "C") AS 
  select  to_char(votedate, 'MON') m, count(*) c
from ranking
group by to_char(votedate,'MON');
