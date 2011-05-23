--------------------------------------------------------
--  File created - Tuesday-May-24-2011   
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
Insert into S3252905.FCONFIG (ID,WELCOME) values ('1','Welcome to fororo forum! hope u have a great time!');
REM INSERTING into S3252905.FTHREAD
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (0,'acid jazz? ',' is acid jazz a kind of jazz?','2','admin','Sun May 22 00:56:09 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (1,'origin of Bossanova','where does bossanova come from? ','3','edd','Sun May 22 00:56:28 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (2,'origin of electro','where does the electronica come from?','4','vic','Sun May 22 00:56:59 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (3,'origin of bouzouki','where does the bouzouki come from? ','5','vic','Sun May 22 00:57:28 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (4,'Bossa Nova under the starts','The new album from BigCotes comming soon','3','admin','Sun May 22 01:03:32 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (5,'Mambo Bouzouki ','is there any bambo bouzouki song?','5','admin','Sun May 22 01:04:48 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (6,'Sasha Involver','everyone msut have','4','john','Sun May 22 01:05:37 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (400,'Re: origin of Bossanova','I dont know but I like Brazilian Girls shaking their body','3','edd','Mon May 23 23:01:54 EST 2011','1');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (7,'where is bbking?','Anyone knows where is this guy?','1','john','Sun May 22 00:55:33 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (8,'origin of jazz ','where does jazz come from? ','2','edd','Sun May 22 00:55:58 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (390,'Re: Re: Re: General rules','Simply Click on the Login Button on the main page.
If you dont have an account just register by clicking the register button
','0','edd','Mon May 23 22:47:04 EST 2011','389');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (391,'Re: What is bbking guitar name?','He Named his Guitar Petra','1','edd','Mon May 23 22:49:46 EST 2011','10');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (394,'Re: Re: How can i learn guitar','Where can I buy one','1','edd','Mon May 23 22:54:02 EST 2011','393');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (395,'Re: Re: Re: How can i learn guitar','www.ebay.com.au','1','edd','Mon May 23 22:54:16 EST 2011','394');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (399,'Re: origin of bouzouki','The Bouzouki was invented by a caveman','5','edd','Mon May 23 22:56:29 EST 2011','3');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (401,'Re: Bossa Nova under the starts','cant wait!!','3','edd','Mon May 23 23:02:59 EST 2011','4');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (381,'Re: acid jazz? ','I dont think so','2','vic','Mon May 23 15:33:04 EST 2011','0');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (385,'Re: where is bbking?','I went to Vietnam an saw him at a Bar','1','edd','Mon May 23 22:37:50 EST 2011','7');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (382,'Re: What is bbking guitar name?','lucile','1','vic','Mon May 23 15:34:59 EST 2011','10');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (387,'Re: Re: where is bbking?','I like Bars in Vietnam','1','edd','Mon May 23 22:38:46 EST 2011','385');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (9,'About Festivals','What the best festivals in melbourne?','2','vic','Sun May 22 01:00:59 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (10,'What is bbking guitar name?','Whats the bb king guitar name?','1','admin','Sun May 22 01:02:03 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (383,'Re: where is bbking?','In Australia, I think','1','edd','Mon May 23 22:36:39 EST 2011','7');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (386,'Re: Re: Re: where is bbking?','But Remember he Likes traveling','1','edd','Mon May 23 22:38:15 EST 2011','384');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (388,'Re: General rules','To be Able to Reply Threads or request a group Creation you must be logged in','0','edd','Mon May 23 22:45:43 EST 2011','15');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (392,'Re: Re: What is bbking guitar name?','Lucile was his moms name','1','edd','Mon May 23 22:53:15 EST 2011','382');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (396,'Re: Re: Re: Re: How can i learn guitar','Are they cheap?','1','edd','Mon May 23 22:54:38 EST 2011','395');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (398,'Re: How can i learn guitar','I have an excellent book','1','edd','Mon May 23 22:55:13 EST 2011','11');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (11,'How can i learn guitar','Doent anyone  know how to learn guitar?','1','john','Sun May 22 00:59:50 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (12,'About vinyls','how can i clean my vinuls?','4','admin','Sun May 22 01:00:44 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (13,'Bossa Nova radio?','does anyone know?','3','edd','Sun May 22 01:04:02 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (384,'Re: Re: where is bbking?','Australia is too far away
','1','edd','Mon May 23 22:37:00 EST 2011','383');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (389,'Re: Re: General rules','How do I Login?','0','edd','Mon May 23 22:46:06 EST 2011','388');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (393,'Re: How can i learn guitar','Just Get a Guitar','1','edd','Mon May 23 22:53:40 EST 2011','11');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (397,'Re: Re: Re: Re: Re: How can i learn guitar','There are always good offers','1','edd','Mon May 23 22:54:55 EST 2011','396');
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (14,'What is the best collections?','What is the best collections anyone must have?','3','john','Sun May 22 01:00:19 EST 2011',null);
Insert into S3252905.FTHREAD (ID,TITLE,DESCRIPTION,TOPICID,USERID,CREATED,PARENTID) values (15,'General rules','the rules go here','0','vic','Sun May 22 01:02:56 EST 2011',null);
REM INSERTING into S3252905.FTOPIC
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (0,'General','Discuss about the this forum','true','admin','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (1,'Blues','From new orleans','true','vic','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (2,'Jazz ','Only for true jazz lovers ','true','edd','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (3,'BossaNova','The sound that seduced the world','true','john','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (4,'Electronica','Dubidubi shake your body','true','john','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (5,'Greek','Oooooopa the bouzouki','true','vic','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (6,'80s','if you ever had funny hair','false','vic','Sun May 22 00:51:03 EST 2011');
Insert into S3252905.FTOPIC (ID,NAME,DESCRIPTION,ISACTIVE,USERID,CREATED) values (741,'Group pending for aproval','to be approved','false','vic','Mon May 23 22:58:15 EST 2011');
REM INSERTING into S3252905.FUSER
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (581,'User1','abcd1234','true','NORMAL','Mon May 23 23:08:49 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (566,'admin','admin','true','ADMIN','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (567,'edd','abcd1234','true','ADMIN','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (568,'john','abcd1234','true','ADMIN','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (569,'vic','abcd1234','true','NORMAL','Sun May 22 00:47:52 EST 2011');
Insert into S3252905.FUSER (ID,NAME,PASSWORD,ISACTIVE,TYPE,CREATED) values (570,'inactive','abcd1234','false','NORMAL','Sun May 22 00:47:52 EST 2011');
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
--  DDL for Index FTOPIC_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "S3252905"."FTOPIC_PK" ON "S3252905"."FTOPIC" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
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
