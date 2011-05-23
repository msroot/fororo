--------------------------------------------------------
--  File created - Monday-May-23-2011   
--------------------------------------------------------
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

   CREATE SEQUENCE  "S3252905"."FTHREAD_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 421 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence FTOPIC_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "S3252905"."FTOPIC_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 761 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence FUSER_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "S3252905"."FUSER_ID_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 601 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence TEST1_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "S3252905"."TEST1_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
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
REM INSERTING into S3252905.FCONFIG
Insert into S3252905.FCONFIG (ID,WELCOME) values ('1','Welcome To Fororo, The Best Forum in the World!');
REM INSERTING into S3252905.FTHREAD
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (398,'    Re:     Re: About Festivals','Tee','2','edd','Sun May 22 19:53:11 EST 2011','397');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (0,'acid jazz? ',' is acid jazz a kind of jazz?','2','admin','Sun May 22 00:56:09 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (1,'origin of Bossanova','where does bossanova come from? ','3','edd','Sun May 22 00:56:28 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (2,'origin of electro','where does the electronica come from?','4','vic','Sun May 22 00:56:59 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (3,'origin of bouzouki','where does the bouzouki come from? ','5','vic','Sun May 22 00:57:28 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (4,'Bossa Nova under the starts','The new album from BigCotes comming soon','3','admin','Sun May 22 01:03:32 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (5,'Mambo Bouzouki ','is there any bambo bouzouki song?','5','admin','Sun May 22 01:04:48 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (6,'Sasha Involver','everyone msut have','4','john','Sun May 22 01:05:37 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (7,'where is bbking?','Anyone knows where is this guy?','1','john','Sun May 22 00:55:33 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (8,'origin of jazz ','where does jazz come from? ','2','edd','Sun May 22 00:55:58 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (9,'About Festivals','What the best festivals in melbourne?','2','vic','Sun May 22 01:00:59 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (10,'What''s the kk bings guitar name?','What''s the kk bings guitar name?','1','admin','Sun May 22 01:02:03 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (11,'How can i learn guitar','Doent anyone  know how to learn guitar?','1','john','Sun May 22 00:59:50 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (12,'About vinyls','how can i clean my vinuls?','4','admin','Sun May 22 01:00:44 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (13,'Bossa Nova radio?','does anyone know?','3','edd','Sun May 22 01:04:02 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (14,'favorite type of jazz?','dudes what''s your favorite type of jazz? is it smooth? contemporary? classic? or something else?','2','edd','Sun May 22 01:05:10 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (15,'What is the best collections?','What is the best collections anyone must have?','2','john','Sun May 22 01:00:19 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (16,'Where is the girl from Ipanema?','Is she married?','3','vic','Sun May 22 01:02:56 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (382,'test','New Thread','0','edd','Sun May 22 16:10:32 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (386,'Re: re: re: acid jazz? ','Reverga','2','edd','Sun May 22 18:43:34 EST 2011','384');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (387,'Re: re: acid jazz? ','asd','2','edd','Sun May 22 18:53:01 EST 2011','17');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (388,'Re: origin of jazz ','Comes From Africa','2','edd','Sun May 22 18:57:54 EST 2011','8');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (391,'Re: Re: where is bbking?','Australia is too Far Away','1','edd','Sun May 22 19:13:18 EST 2011','390');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (393,'    Re: Re: origin of Bossanova','Its too cold in Canada','3','edd','Sun May 22 19:24:33 EST 2011','392');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (399,'Re:     Re:     Re: About Festivals','eerrr','2','edd','Sun May 22 19:58:54 EST 2011','398');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (17,'re: acid jazz? ','I don;t think so bro, is more electronic stuff','2','vic','Sun May 22 16:10:32 EST 2011','0');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (384,'re: re: acid jazz? ','how can u be so sure?','2','vic','Sun May 22 16:10:32 EST 2011','0');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (389,'Re: where is bbking?','in russia','1','edd','Sun May 22 19:05:14 EST 2011','7');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (392,'  Re: origin of Bossanova','from Canada','3','vic','Sun May 22 19:17:29 EST 2011','1');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (394,'        Re: Re: origin of Bossanova','Ducks like it anyway','3','edd','Sun May 22 19:26:50 EST 2011','393');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (401,'Re: Re: where is bbking?','Its right there dude','1','edd','Sun May 22 23:54:52 EST 2011','389');
REM INSERTING into S3252905.FTOPIC
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (0,'General','Discuss about the this forum','true','admin','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (1,'Blues','From new orleans','true','vic','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (2,'Jazz ','Only for true jazz lovers ','true','edd','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (3,'BossaNova','The sound that seduced the world','true','john','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (4,'Electronica','Dubidubi shake your body','true','john','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (5,'Greek','Oooooopa the bouzouki','true','vic','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (6,'80s','if you ever had funny hair','false','vic','Sun May 22 00:51:03 EST 2011');
REM INSERTING into S3252905.FUSER
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (566,'admin','admin','true','ADMIN','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (567,'edd','abcd1234','true','ADMIN','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (568,'john','abcd1234','true','ADMIN','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (569,'vic','abcd1234','true','NORMAL','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (570,'inactive','abcd1234','false','NORMAL','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (584,'plomo','     ','true','NORMAL','Sun May 22 15:19:36 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (587,'new','abcd','true','NORMAL','Sun May 22 15:57:24 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (589,'newAdmin','admin','true','ADMIN','Sun May 22 16:04:17 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (583,'asdf','fasd','true','NORMAL','Sun May 22 14:54:31 EST 2011');
--------------------------------------------------------
--  DDL for Index FTOPIC_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "S3252905"."FTOPIC_PK" ON "S3252905"."FTOPIC" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index FTHREAD_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "S3252905"."FTHREAD_PK" ON "S3252905"."FTHREAD" ("ID") 
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
--  Constraints for Table FTHREAD
--------------------------------------------------------

  ALTER TABLE "S3252905"."FTHREAD" ADD CONSTRAINT "FTHREAD_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "S3252905"."FTHREAD" MODIFY ("ID" NOT NULL ENABLE);
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
--  Constraints for Table FTOPIC
--------------------------------------------------------

  ALTER TABLE "S3252905"."FTOPIC" ADD CONSTRAINT "FTOPIC_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "S3252905"."FTOPIC" MODIFY ("ID" NOT NULL ENABLE);
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
