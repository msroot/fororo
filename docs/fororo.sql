
  CREATE TABLE "S3252905"."FTHREAD" 
   (	"ID" NUMBER NOT NULL ENABLE, 
	"TITLE" VARCHAR2(20 BYTE), 
	"DESCRIPTION" VARCHAR2(20 BYTE), 
	"TOPICID" VARCHAR2(20 BYTE), 
	 CONSTRAINT "FTHREAD_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
 

  CREATE OR REPLACE TRIGGER "S3252905"."FTHREAD_INSERT" 
before insert on FTHREAD
for each row
begin
    select FTHREAD_id_seq.nextval into :new.id from dual;
end;
/
ALTER TRIGGER "S3252905"."FTHREAD_INSERT" ENABLE;
 

  CREATE TABLE "S3252905"."FTOPIC" 
   (	"ID" NUMBER NOT NULL ENABLE, 
	"NAME" VARCHAR2(20 BYTE), 
	"DESCRIPTION" VARCHAR2(20 BYTE), 
	"ISACTIVE" VARCHAR2(20 BYTE), 
	 CONSTRAINT "FTOPIC_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
 

  CREATE OR REPLACE TRIGGER "S3252905"."FTOPIC_INSERT" 
before insert on FTOPIC
for each row
begin
    select FTOPIC_id_seq.nextval into :new.id from dual;
end;
/
ALTER TRIGGER "S3252905"."FTOPIC_INSERT" ENABLE;
 

  CREATE TABLE "S3252905"."FUSER" 
   (	"ID" NUMBER NOT NULL ENABLE, 
	"NAME" VARCHAR2(20 BYTE), 
	"PASSWORD" VARCHAR2(20 BYTE), 
	"ISACTIVE" VARCHAR2(20 BYTE), 
	"TYPE" VARCHAR2(20 BYTE), 
	 CONSTRAINT "FUSER_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
 

  CREATE OR REPLACE TRIGGER "S3252905"."USER_INSERT" 
before insert on FUSER
for each row
begin
    select FUSER_id_seq.nextval into :new.id from dual;
end;
/
ALTER TRIGGER "S3252905"."USER_INSERT" ENABLE;
 
