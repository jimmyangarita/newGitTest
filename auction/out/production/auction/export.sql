--------------------------------------------------------
--  File created - Monday-October-27-2014   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CATEGORY
--------------------------------------------------------

  CREATE TABLE "CDMR"."CATEGORY" 
   (	"DESCRIPTION" VARCHAR2(255 BYTE), 
	"LASTMODIFIED" TIMESTAMP (6), 
	"NAME" VARCHAR2(255 BYTE), 
	"VERSION" NUMBER(19,0), 
	"OID" VARCHAR2(36 BYTE), 
	"PARENTCATEGORYID" VARCHAR2(36 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Table ITEM
--------------------------------------------------------

  CREATE TABLE "CDMR"."ITEM" 
   (	"AUCTIONDURATION" NUMBER(10,0), 
	"BIDENDDATE" TIMESTAMP (6), 
	"BIDSTARTDATE" TIMESTAMP (6), 
	"DESCRIPTION" VARCHAR2(2000 BYTE), 
	"INITIALPRICE" NUMBER(19,4), 
	"ITEMCONDITION" VARCHAR2(255 BYTE), 
	"LASTMODIFIED" TIMESTAMP (6), 
	"PAYMENTMETHOD" VARCHAR2(255 BYTE), 
	"PICTURE" BLOB, 
	"POSTDATE" TIMESTAMP (6), 
	"POSTAGECOST" NUMBER(19,4), 
	"RESERVEDPRICE" NUMBER(19,4), 
	"TITLE" VARCHAR2(255 BYTE), 
	"VERSION" NUMBER(19,0), 
	"OID" VARCHAR2(36 BYTE), 
	"ORDERID" VARCHAR2(36 BYTE), 
	"SELLERID" VARCHAR2(36 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" 
 LOB ("PICTURE") STORE AS BASICFILE (
  TABLESPACE "CDMR_TABLESPACE" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Table ITEM_CATEGORY
--------------------------------------------------------

  CREATE TABLE "CDMR"."ITEM_CATEGORY" 
   (	"CATEGORYID" VARCHAR2(36 BYTE), 
	"ITEMID" VARCHAR2(36 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Table SELLER
--------------------------------------------------------

  CREATE TABLE "CDMR"."SELLER" 
   (	"OID" VARCHAR2(36 BYTE), 
	"CREDITWORTH" NUMBER(19,4)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "CDMR"."USERS" 
   (	"USERTYPE" VARCHAR2(1 BYTE), 
	"OID" VARCHAR2(36 BYTE), 
	"CREATIONDATE" TIMESTAMP (6), 
	"EMAIL" VARCHAR2(255 BYTE), 
	"FISRTNAME" VARCHAR2(255 BYTE), 
	"LASTMODIFIED" TIMESTAMP (6), 
	"LASTNAME" VARCHAR2(255 BYTE), 
	"PASSWORD" VARCHAR2(255 BYTE), 
	"PHONE" VARCHAR2(255 BYTE), 
	"USERNAME" VARCHAR2(255 BYTE), 
	"VERSION" NUMBER(19,0), 
	"CITY" VARCHAR2(255 BYTE), 
	"COUNTRY" VARCHAR2(255 BYTE), 
	"STATE" VARCHAR2(255 BYTE), 
	"STREELINE2" VARCHAR2(255 BYTE), 
	"STREETLINE1" VARCHAR2(255 BYTE), 
	"ZIPCODE" VARCHAR2(255 BYTE), 
	"BILLINGINFOID" VARCHAR2(36 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Table USERS_CATEGORY
--------------------------------------------------------

  CREATE TABLE "CDMR"."USERS_CATEGORY" 
   (	"CATEGORYID" VARCHAR2(36 BYTE), 
	"USERID" VARCHAR2(36 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Table USERSPICTURE
--------------------------------------------------------

  CREATE TABLE "CDMR"."USERSPICTURE" 
   (	"OID" VARCHAR2(36 BYTE), 
	"PICTURE" BLOB
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" 
 LOB ("PICTURE") STORE AS BASICFILE (
  TABLESPACE "CDMR_TABLESPACE" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Table BIDDER
--------------------------------------------------------

  CREATE TABLE "CDMR"."BIDDER" 
   (	"OID" VARCHAR2(36 BYTE), 
	"BIDFRECUENCY" NUMBER(19,4)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Table BID
--------------------------------------------------------

  CREATE TABLE "CDMR"."BID" 
   (	"BIDAMOUNT" NUMBER(19,4), 
	"BIDDATE" TIMESTAMP (6), 
	"LASTMODIFIED" TIMESTAMP (6), 
	"MAXAMOUNT" NUMBER(19,4), 
	"VERSION" NUMBER(19,0), 
	"OID" VARCHAR2(36 BYTE), 
	"BIDDERID" VARCHAR2(36 BYTE), 
	"ITEMID" VARCHAR2(36 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Table BILLINGINFO
--------------------------------------------------------

  CREATE TABLE "CDMR"."BILLINGINFO" 
   (	"BANKACCOUNTNUMBER" VARCHAR2(255 BYTE), 
	"BANKNAME" VARCHAR2(255 BYTE), 
	"CREDITCARDEXPIRATION" DATE, 
	"CREDITCARDNAMBER" VARCHAR2(255 BYTE), 
	"CREDITCARDTYPE" VARCHAR2(255 BYTE), 
	"LASTMODIFIED" TIMESTAMP (6), 
	"NAMEONCREDITCARD" VARCHAR2(255 BYTE), 
	"ROUTINGNUMBER" VARCHAR2(255 BYTE), 
	"VERSION" NUMBER(19,0), 
	"OID" VARCHAR2(36 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Table ADDRESS
--------------------------------------------------------

  CREATE TABLE "CDMR"."ADDRESS" 
   (	"IPADDRESS" VARCHAR2(255 BYTE), 
	"LASTMODIFIED" TIMESTAMP (6), 
	"VERSION" NUMBER(19,0), 
	"OID" VARCHAR2(36 BYTE), 
	"OBJECTID" VARCHAR2(36 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "CDMR_TABLESPACE" ;
REM INSERTING into CDMR.CATEGORY
SET DEFINE OFF;
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('HP Laptop Computers',to_timestamp('27-OCT-14 09.56.39.072000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'HP',1,'890d7263-7f7e-4fb2-99c1-f5f1cf6b0ddf','f2a01e20-815d-4cbe-bd0b-a776949448d8');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Computers Monitors',to_timestamp('27-OCT-14 09.56.39.073000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Computers Monitors',1,'71c988da-7da2-4139-ae26-cab8ba0f0bb8','ef59bf20-1d69-4607-8340-df359c63e092');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Science and Math Books',to_timestamp('27-OCT-14 09.56.39.067000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Science and Math',1,'0439ed4e-9318-4959-8299-6d81fb2138b9','d37cfbff-e1ac-41ec-a8b9-c2e12a65d526');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Technology Science and Math Books',to_timestamp('27-OCT-14 09.56.39.069000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Technology',1,'76a8f19b-4249-409c-9d14-913d1b4b0371','0439ed4e-9318-4959-8299-6d81fb2138b9');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Math Science and Math Books',to_timestamp('27-OCT-14 09.56.39.068000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Math',1,'d905dce0-6732-4f0b-b7ed-3ab263d071d1','0439ed4e-9318-4959-8299-6d81fb2138b9');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Womam World History Books',to_timestamp('27-OCT-14 09.56.39.065000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Woman',1,'242b3b1f-7fa5-46a7-8c84-3f2e7f9e69ce','7224153e-3915-48f7-88ed-1710f987051d');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Religion World History Books',to_timestamp('27-OCT-14 09.56.39.065000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Religion',1,'c8077878-13de-4a41-94fb-ad63a44c2368','7224153e-3915-48f7-88ed-1710f987051d');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Asus Computers Monitor',to_timestamp('27-OCT-14 09.56.39.073000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Asus',1,'f1644308-acd3-4917-8cc5-1761795a8059','71c988da-7da2-4139-ae26-cab8ba0f0bb8');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Physics Science and Math Books',to_timestamp('27-OCT-14 09.56.39.068000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Physics',1,'a2d22521-77fe-4c7c-a06f-2d8e75653b71','0439ed4e-9318-4959-8299-6d81fb2138b9');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Dell Computers Monitors',to_timestamp('27-OCT-14 09.56.39.074000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Dell',1,'efc80749-59f3-4b99-96e8-1c1a1583dae2','71c988da-7da2-4139-ae26-cab8ba0f0bb8');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('World War II Military History Books',to_timestamp('27-OCT-14 09.56.39.067000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'World War II',1,'c5900e0d-ed24-458e-a6f8-8889d44a4596','955f596a-89fa-4015-b95a-26f69e76d7a4');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Computers and Accessories Category',to_timestamp('27-OCT-14 09.56.39.069000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Computers and Accessories',1,'ef59bf20-1d69-4607-8340-df359c63e092',null);
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Laptop Computers',to_timestamp('27-OCT-14 09.56.39.071000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Laptop Computer',1,'f2a01e20-815d-4cbe-bd0b-a776949448d8','ef59bf20-1d69-4607-8340-df359c63e092');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Desktop Computers',to_timestamp('27-OCT-14 09.56.39.070000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Desktop Computers',1,'7fdeef13-d60d-42cb-bcc5-53f773fe4d8d','ef59bf20-1d69-4607-8340-df359c63e092');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Dell Desktop Computers',to_timestamp('27-OCT-14 09.56.39.070000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Dell',1,'b47d87fc-c4c9-4cea-abbe-d70f98036893','7fdeef13-d60d-42cb-bcc5-53f773fe4d8d');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Books Category',to_timestamp('27-OCT-14 09.56.39.063000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Books',1,'d37cfbff-e1ac-41ec-a8b9-c2e12a65d526',null);
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('History Books',to_timestamp('27-OCT-14 09.56.39.064000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'History',1,'22425e3c-4e81-47eb-9a5e-ce4cc2fc48cd','d37cfbff-e1ac-41ec-a8b9-c2e12a65d526');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Military History Books',to_timestamp('27-OCT-14 09.56.39.066000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Military',1,'955f596a-89fa-4015-b95a-26f69e76d7a4','22425e3c-4e81-47eb-9a5e-ce4cc2fc48cd');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('World War Military History Books',to_timestamp('27-OCT-14 09.56.39.067000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'World War I',1,'23137175-9f22-40ab-b06c-a67498575e4a','955f596a-89fa-4015-b95a-26f69e76d7a4');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('HP Desktop Computers',to_timestamp('27-OCT-14 09.56.39.071000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'HP',1,'d9fd2ed1-187b-42d8-9a2d-46d64b84b9d8','7fdeef13-d60d-42cb-bcc5-53f773fe4d8d');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Dell Laptop Computers',to_timestamp('27-OCT-14 09.56.39.072000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Dell',1,'7ba95195-28ab-4d3a-ae12-61936cc8f7f9','f2a01e20-815d-4cbe-bd0b-a776949448d8');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('Inteligence and Espionage Military History Books',to_timestamp('27-OCT-14 09.56.39.066000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Inteligence and Espionage',1,'8b50cea8-bf4c-4ad8-8027-43824be74280','955f596a-89fa-4015-b95a-26f69e76d7a4');
Insert into CDMR.CATEGORY (DESCRIPTION,LASTMODIFIED,NAME,VERSION,OID,PARENTCATEGORYID) values ('World History Books',to_timestamp('27-OCT-14 09.56.39.064000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'World',1,'7224153e-3915-48f7-88ed-1710f987051d','22425e3c-4e81-47eb-9a5e-ce4cc2fc48cd');
REM INSERTING into CDMR.ITEM
SET DEFINE OFF;
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 11.42.30.769000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 11.42.30.769000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Building on the bestselling first edition, EJB 3 in Action, Second Edition tackles EJB 3.2 head-on, through numerous code samples, real-life scenarios, and illustrations. This book is a fast-paced tutorial for Java EE 6 business component development using EJB 3.2, JPA 2, and CDI. Besides covering the basics of EJB 3.2, this book includes in-depth EJB 3.2 internal implementation details, best practices, design patterns, and performance tuning tips.

Purchase of the print book includes a free eBook in PDF, Kindle, and ePub formats from Manning Publications',43.68,'NEW',to_timestamp('27-OCT-14 11.42.30.779000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 11.42.30.769000000 AM','DD-MON-RR HH.MI.SS.FF AM'),0,51.66,'EJB 3 in action',1,'4c535f15-752c-41fe-ab6a-4357d67b8611',null,'30a0af8f-3682-4cf6-bea3-e68e345dfa81');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 11.45.23.647000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 11.45.23.647000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'“SOA Design Patterns is an important contribution to the literature and practice 
of building and delivering quality software-intensive systems.”
- Grady Booch, IBM Fellow
“With the continued explosion of services and the increased rate of adoption of SOA through the market, there is a critical need for comprehensive, actionable guidance that provides the fastest possible time to results. Microsoft is honored to contribute to the SOA Design Patterns book, and to continue working with the community to realize the value of Real World SOA.”
- Steven Martin, Senior Director, Developer Platform Product Management, Microsoft
',34.68,'NEW',to_timestamp('27-OCT-14 11.45.23.660000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 11.45.23.647000000 AM','DD-MON-RR HH.MI.SS.FF AM'),0,47.66,'SOA Design Patterns',1,'b4d03f5c-ada5-42d6-8734-e4e754ed8606',null,'30a0af8f-3682-4cf6-bea3-e68e345dfa81');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 09.56.39.057000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 09.56.39.057000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'?',10,'NEW',to_timestamp('27-OCT-14 09.56.39.063000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 09.56.39.057000000 AM','DD-MON-RR HH.MI.SS.FF AM'),0,240,'?',1,'8d194146-f80d-4121-bcb2-758924e54b53',null,'30a0af8f-3682-4cf6-bea3-e68e345dfa81');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 11.20.51.660000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 11.20.51.660000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'JavaServer Faces (JSF) is the standard Java EE technology for building web user interfaces. It provides a powerful framework for developing server-side applications, allowing you to cleanly separate visual presentation and application logic. JSF 2.0 is a major upgrade, which not only adds many useful features but also greatly simplifies the programming model by using annotations and “convention over configuration” for common tasks.',35,'NEW',to_timestamp('27-OCT-14 11.20.51.687000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 11.20.51.660000000 AM','DD-MON-RR HH.MI.SS.FF AM'),0,55,'Core Java Server Faces (3rd edition)',1,'5faf2a9d-502e-4a30-a93e-79a90c3810c7',null,'30a0af8f-3682-4cf6-bea3-e68e345dfa81');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 02.48.53.480000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 02.48.53.480000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'Walker uses an integrated "suite" of tools, worked Examples, Active Examples, and Conceptual Checkpoints, to make conceptual understanding an integral part of solving quantitative problems. The pedagogy and approach are based on over 20 years of teaching and reflect the results of physics education research.
',34.68,'NEW',to_timestamp('27-OCT-14 02.48.53.558000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 02.48.53.480000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,47.66,'Physics, Volume 1 (3r Edition) James S. Walker 1 Apostol',1,'a28806f2-db3b-426c-825d-08043bd3be1a',null,'30a0af8f-3682-4cf6-bea3-e68e345dfa81');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 11.56.55.512000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 11.56.55.512000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'This edition differs from the third mainly in the inclusion of additional problems, as well as a complete update of the Suggested Reading, together with some changes of exposition, mainly in Chapters 5 and 20
',34.68,'NEW',to_timestamp('27-OCT-14 11.56.55.525000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 11.56.55.512000000 AM','DD-MON-RR HH.MI.SS.FF AM'),0,47.66,'Calculus, 4th edition Spivak',1,'52e716cf-f5e1-48fe-aca0-5e55bf8f21ea',null,'30a0af8f-3682-4cf6-bea3-e68e345dfa81');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 12.00.37.716000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 12.00.37.716000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'One-Variable Calculus, with an Introduction to Linear Algebra
',34.68,'NEW',to_timestamp('27-OCT-14 12.00.37.726000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 12.00.37.716000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,47.66,'Calculus, Vol, 1 Apostol',1,'29bb730c-5ba1-4f0e-bdbd-9b4134965cff',null,'30a0af8f-3682-4cf6-bea3-e68e345dfa81');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.34.45.065000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.34.45.064000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
	World War II The Book Hard Cover
	 ',34.68,'NEW',to_timestamp('27-OCT-14 03.34.45.142000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 03.34.45.065000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,47.66,'World War II The Book',1,'e6e2dc72-c9f5-4167-a22b-53a74294d183',null,'a4fd0e7e-0cee-44d3-9e33-b67622026de7');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.41.52.091000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.41.52.091000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
World War I the Book, Hard Cover
	 ',34.68,'NEW',to_timestamp('27-OCT-14 03.41.52.099000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 03.41.52.091000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,47.66,'World War I the Book',1,'2b102c3a-b05d-4bbc-8eb4-d3c9d8579f02',null,'a4fd0e7e-0cee-44d3-9e33-b67622026de7');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.37.25.275000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.37.25.275000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
Spies, Traitors and the Real Forces Behind World Events
	 ',34.68,'NEW',to_timestamp('27-OCT-14 03.37.25.284000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 03.37.25.275000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,47.66,'The Puppet Masters',1,'e6eb5d3f-9f7c-4fc9-82bc-0dcf26e3095c',null,'a4fd0e7e-0cee-44d3-9e33-b67622026de7');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.38.18.998000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.38.18.998000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
For more than half a century, Big Safari-modified aircraft have performed dangerous and essential missions to collect intelligence, conduct surveillance and reconnaissance, and engage in special operations missions around the globe in the interest of national security. These state-of-the-art aircraft have been flown, operated, and maintained by men and women whose dedication and commitment have made the world a safer place. In The History of Big Safari, author Colonel Bill Grimes, a retired US Air Force officer, presents a history of this program, which has been in existence for more than sixty years. Born as a special acquisition program in 1952, Big Safari has been in a unique position to save lives by rapidly fielding essential systems with a quick-reaction capability to ensure decision makers on the battlefield and at the Pentagon have timely intelligence to plan and execute operations. Grimes shows how, without a special acquisition program such as Big Safari, the nation''s ability to react to evolving dangers and threats would be mired in bureaucracy when timely responses are critical. With detailed cutaway illustrations revealing aircraft modifications and mission equipment, The History of Big Safari also includes photographs, sidebars, and anecdotes. It goes behind the scenes with the men and women who participated in the challenging projects and daring missions. It shares the development of cutting-edge technology and special mission aircraft, as well as the global events that necessitated these once-classified programs. Finally, it provides insight into long-veiled projects, operations, and missions that comprise the world under the purview of Big Safari.
	 ',34.68,'NEW',to_timestamp('27-OCT-14 03.38.19.009000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 03.38.18.998000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,47.66,'The History of Big Safari',1,'c0e642b7-676e-4faf-8762-990131413838',null,'a4fd0e7e-0cee-44d3-9e33-b67622026de7');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.45.13.602000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.45.13.602000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
Contemporary styling: Feel like you?re there with an elegant, virtually borderless 21.5" display featuring an almost-invisible bezel.
Superb performance: Images come alive with color-boosting Image Enhance, ultrawide viewing and Full HD resolution (1920 x 1080 max.) in a 16:9 widescreen format.
Versatile connections: Easily connect to your laptop or desktop with VGA and DVI-D (HDCP) connectivity.
	 ',134.68,'NEW',to_timestamp('27-OCT-14 03.45.13.612000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 03.45.13.602000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,147.67,'Dell S2240M CFGKT-IPS-LED 21.5-Inch Screen LED-lit Monitor',1,'995761de-282f-4b36-9c02-8062c186a6b3',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.49.04.677000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.49.04.677000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* High-resolution widescreen 18.5" display has the features you need to help boost productivity.
* Rigorous testing and process-quality monitoring help ensure long-term performance reliability.
* Built to comply with the latest environmental and regulatory standards.
* Compatible with the Dell AC511 Sound bar
	 ',134.68,'NEW',to_timestamp('27-OCT-14 03.49.04.687000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 03.49.04.677000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,147.67,'Dell E1914H 19-Inch Screen LED-Lit Monitor',1,'b4028bc0-7d1f-44b0-a6d2-baa3c6882d1b',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.50.53.729000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.50.53.729000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* Contemporary styling: Experience a lifelike view with an elegant, virtually borderless 23" display featuring an almost-invisible bezel.
*  Superb performance: Images come alive with color-boosting Image Enhance, ultra-wide viewing and Full HD resolution (1920 x 1080 max.) in a 16:9 widescreen format.
* Versatile connections: Easily connect to your laptop or desktop with VGA and DVI-D (HDCP) connectivity.
	 ',134.68,'NEW',to_timestamp('27-OCT-14 03.50.53.739000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 03.50.53.729000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,147.67,'Dell S2340M 293M3-IPS-LED 23-Inch Screen LED-lit Monitor',1,'0ec1d9d7-1288-4aa8-9084-6c7064c56858',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.53.15.819000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.53.15.819000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
*  Slim Form Factor - Brand new Slim and Elegant design; Built-in Power Adapter; VESA Mountable
* Corporate Stable Model ensuring 1 year stable supply and advanced swap
* EPEAT Gold Certified
*  Versatile viewing positions with Smart View Technology
* Excellent Visual - LED Backlit with 50,000,000:1 ASUS Smart Contrast Ratio; Full 1080P with HDMI; TN Panel Display
	 ',136.68,'NEW',to_timestamp('27-OCT-14 03.53.15.832000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 03.53.15.819000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,199.67,'Asus VS247H-P 23.6-Inch Full-HD LED-Lit LCD Monitor',1,'2127e20c-614e-41c1-88db-a0a130add290',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.54.52.803000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.54.52.803000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* TFT-LCD Panel-Panel Size: 22-Inch, Wide Screen, Color Saturation: 68%(NTSC), True Resolution: 1920x1080, Pixel Pitch: 0.248mm
* Brightness(Max): 250 cd/m2, Contrast Ratio (Max.): 10,000,000:1 (ASCR), Display Color: 16.7M, Response: 5ms;
* Input/Output -PC Input: DVI-D/D-Sub, PC Audio Input: 3.5mm Mini-jack, Video Input: HDMI, AV * Audio Input: HDMI 1.3, Earphone jack: 3.5mm Mini-jack
* 1W x 2 Built-in stereo speakers RMS ASUS Splendid Technology
	 ',136.68,'NEW',to_timestamp('27-OCT-14 03.54.52.812000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 03.54.52.803000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,199.67,'Asus VE228H 21.5-Inch Full-HD LED Monitor with Integrated Speakers',1,'e938a454-10d3-43ae-bfdd-ebe362357b02',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.58.59.980000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.58.59.980000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* True-to-life Pictures Powered by LED, Full HD 1080p support
* 1ms Response time, Aspect Control Function
* Extensive Connectivity and Built-in Stereo Speaker for Multimedia Enjoyment, Splendid video technology
* Stylish and User-friendly Design for Modern Sophistication
* Complete After-Sale Service, 3-year system warranty
	 ',136.68,'USED_LIKE_NEW',to_timestamp('27-OCT-14 03.58.59.989000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CS',to_timestamp('27-OCT-14 03.58.59.980000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,199.67,'ASUS VS278Q-P Ultrafast 1ms 27-Inch LED-Lit Monitor',1,'05fca330-5289-4c45-b488-41ad09757d95',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 04.01.34.782000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 04.01.34.782000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* Full HD with HDMI: Enter a spectacular world of colors with Full HD 1080p (1920x1080) support and HDMI port.
50,000,000:1 
* ASUS Smart Contrast Ratio; Dynamically enhances the display''s contrast by adjusting the luminance of the backlight to achieve the darkest black and brightest white - delivering lifelike images.
2ms Response Time - Quick response time of 2ms (GTG) eliminates ghosting and tracers for more fluid video playback.
* Aspect Control function: Aspect Control function allows users to select a preferred display mode among Full and 4:3 for true-to-life gaming or movie watching without any data loss or image distortion.
	 ',56.68,'USED_ACCEPTABLE',to_timestamp('27-OCT-14 04.01.34.802000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 04.01.34.782000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,69.67,'ASUS VK248H-CSM 24-Inch Full-HD LED-Lit LCD Monitor with Integrated Speakers and Webcam',1,'2f81a389-fa21-4908-8eb5-b5ba19df7c73',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.29.22.714000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.29.22.714000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
	The Famous Air Combats that Defined WWI (A Higher Call, Air Combat, War Torn Skies, World War 2, WWII, unbroken, World War II, Aviation, Air Force, Military Book 1) 
	 ',34.68,'NEW',to_timestamp('27-OCT-14 03.29.22.729000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 03.29.22.714000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,47.66,'World War 2 Air Battles',1,'137e99f0-4ef5-4646-b86a-1cf7f6b87774',null,'a4fd0e7e-0cee-44d3-9e33-b67622026de7');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.07.30.533000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.07.30.533000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
	A classic early 20th century translation by the Jewish Publication Society is graced by reproductions of ancient frescoes, medieval illuminated manuscripts, and paintings by contemporary artists.
	 ',34.68,'NEW',to_timestamp('27-OCT-14 03.07.30.545000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 03.07.30.534000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,47.66,'The Torah',1,'5b455439-c3f4-4449-89ab-6c032b7af0e4',null,'a4fd0e7e-0cee-44d3-9e33-b67622026de7');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 03.25.36.722000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 03.25.36.722000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
	Gracie (Women and War Book 1) hard cover
	 ',34.68,'NEW',to_timestamp('27-OCT-14 03.25.36.789000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 03.25.36.722000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,47.66,'Gracie (Women and War Book 1)',1,'f24ca393-c577-4443-8919-6e28331fc96e',null,'a4fd0e7e-0cee-44d3-9e33-b67622026de7');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 04.11.22.771000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 04.11.22.771000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* Intel Core i3-4130 Processor, Intel HD Graphics 4400
* 4GB PC3-12800 DDR3-1600 SDRAM memory 1x4GB (expandable 16GB)
* 1 TB Hard Drive, 7200 rpm, SATA, SuperMulti DVD Burner
* HP 7-in-1 Media Card Reader, 4 x USB 3.0 and 4 x USB 2.0 ports: For fast digital video, audio and data transfer.
* Wireless LAN 802.11b/g/n featuring Single-band (2.4Ghz) 1x1
	 ',336.68,'USED_ACCEPTABLE',to_timestamp('27-OCT-14 04.11.22.866000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CS',to_timestamp('27-OCT-14 04.11.22.771000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,399.67,'HP Pavilion 500-336 Desktop Computer',1,'be0d464a-16b5-4134-85d7-83d7282db6ed',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 02.58.22.564000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 02.58.22.564000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
	The Books of the Bible Hard Cover
	 ',34.68,'NEW',to_timestamp('27-OCT-14 02.58.22.580000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CC',to_timestamp('27-OCT-14 02.58.22.564000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,47.66,'The Books of the Bible',1,'5c77e0c3-e99c-4f4f-bec3-1e47af2d9165',null,'a4fd0e7e-0cee-44d3-9e33-b67622026de7');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 04.33.28.256000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 04.33.28.256000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* Intel Celeron N2830 Processor
* 4 GB DDR3
* 500 GB Hard Drive
* 15.6-Inch Screen, Intel HD Graphics
* Windows 8.1
	 ',936.68,'NEW',to_timestamp('27-OCT-14 04.33.28.278000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CA',to_timestamp('27-OCT-14 04.33.28.256000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,1099.67,'Dell Inspiron 15R 15.6-Inch Laptop Intel processor 4G 500G',1,'9a6cc926-8d36-4be1-b1fd-db06a06793d0',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 04.19.32.638000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 04.19.32.638000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* AMD E2-3800 (Kabini) - Quad Core 1.3 GHz
* 4 GB
* 500 GB 7200 rpm Hard Drive
* 23-Inch Screen, AMD Radeon HD 8280 Graphics
	 ',336.68,'USED_VERY_GOOD',to_timestamp('27-OCT-14 04.19.32.648000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CA',to_timestamp('27-OCT-14 04.19.32.638000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,399.67,'HP Pavilion 23-g010 23-Inch All-in-One Desktop',1,'336a21c8-3cb0-4a14-a622-b37d1440be69',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 04.22.29.214000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 04.22.29.214000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* Intel Core i7-4790 Processor
* 16GB DDR3
* 2 TB Hard Drive
* Windows 8.1
	 ',936.68,'NEW',to_timestamp('27-OCT-14 04.22.29.224000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CA',to_timestamp('27-OCT-14 04.22.29.214000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,1099.67,'Dell XPS X8700-3130BLK Desktop',1,'b3d51779-0429-48a3-9095-6641b132d17b',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 04.35.02.399000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 04.35.02.399000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* Intel Core i3-4030U Processor
* 4GB DDR3
* 500 GB Hard Drive
* 15.6-Inch Screen, Intel HD Graphics 4400
* Windows 7 Home Premium 64-bit
	 ',936.68,'NEW',to_timestamp('27-OCT-14 04.35.02.411000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CA',to_timestamp('27-OCT-14 04.35.02.399000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,1099.67,'Dell Inspiron i3542-6000BK 15.6-Inch Laptop (Windows 7)
		',1,'d4bc06a8-1f93-4b95-9a6b-6713a5dd61cf',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 04.36.42.590000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 04.36.42.590000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* Intel Pentium N3530 Processor
* 4 GB DDR3L SDRAM
* 500 GB 5400 rpm Hard Drive
* 11.6-Inch Screen, Intel HD Graphics
* Windows 8.1, 7.5-hour battery life
	 ',349,'NEW',to_timestamp('27-OCT-14 04.36.42.601000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CA',to_timestamp('27-OCT-14 04.36.42.590000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,400,'
Dell Inspiron i3147-3750sLV 11.6-Inch 2 in 1 Convertible Touchscreen Laptop
		',1,'abc73f4c-cc32-4f16-b7fa-149cf8db4655',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 04.39.28.766000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 04.39.28.766000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* 17.3" Full HD BrightView LED-backlit Touchscreen Display (1920 x 1080)
* 4th Gen Intel Core i7-4700MQ 2.40 GHz (boost up to 3.40 GHz)
* 8GB Memory / 1TB HDD / Supermulti DVD Burner
* Beats Audio / Intel HD 4600 / Backlit Keyboard /6-Cell Li-ion Battery
* Windows 8.1 (64-bit) Operating System Preinstaled
	 ',949,'NEW',to_timestamp('27-OCT-14 04.39.28.779000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CA',to_timestamp('27-OCT-14 04.39.28.766000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,1100,'
HP 17.3" Envy Touchsmart Laptop - 17.3-inch FULL HD Touchscreen, 4th Gen Intel i7-4700MQ 2.4GHz Processor, 8GB DDR3, 1TB HDD, DVDRW, Beats Audio, Backlit Keyboard, Window 8.1
		',1,'140237c7-01fc-4320-aa57-ce059c406314',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 04.40.43.501000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 04.40.43.501000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* AMD A6-6310 Processor
* 4 GB DDR3L SDRAM
* 750 GB 5400 rpm Hard Drive
* 15.6-Inch Screen
* Windows 7 Home Premium, 5.25-hour battery life
	 ',949,'NEW',to_timestamp('27-OCT-14 04.40.43.512000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CA',to_timestamp('27-OCT-14 04.40.43.501000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,1100,'
HP 15-g080nr 15.6-Inch Laptop (Windows 7)
		',1,'ca0e06d9-9ec8-4bb8-a151-0cafb95e9af1',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 04.41.29.282000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 04.41.29.282000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* Front facing HP TrueVision HD Webcam with integrated microphone, HDMI, 2 SuperSpeed USB 3.0
* AMD Elite Quad-Core A8-5550M Accelerated Processor with AMD Radeon HD 8550G graphics with up to 2158MB total graphics memory, 4MB L2 Cache
* 17-inch diagonal HD+ BrightView LED-backlit display (1600 x 900)
* 4GB DDR3 Memory / 750GB Hard Drive / 1 Headphone-out/microphone-in combo jack
* Microsoft Windows 8 64-bit operating system, DTS Sound+
	 ',449,'NEW',to_timestamp('27-OCT-14 04.41.29.292000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CA',to_timestamp('27-OCT-14 04.41.29.282000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,550,'
HP Pavilion 17.3" Laptop Computer - AMD Elite Quad-Core A8-5550M / 4GB DDR3 / 750GB Hard Drive / Anodized Silver / Windows 8
		',1,'02031c34-2984-4123-bb00-ff2b7c5b3040',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 04.42.27.196000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 04.42.27.196000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* Intel Celeron N2830 Processor
* 4 GB DDR3
* 500 GB Hard Drive
* 15.6-Inch Screen, Intel HD Graphics
* Windows 8.1
	 ',250,'NEW',to_timestamp('27-OCT-14 04.42.27.206000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CA',to_timestamp('27-OCT-14 04.42.27.196000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,300,'
Dell Inspiron 15R 15.6-Inch Laptop Intel processor 4G 500G	
		',1,'0b6b7ed2-943f-4583-b09f-dee0a67f7824',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.ITEM (AUCTIONDURATION,BIDENDDATE,BIDSTARTDATE,DESCRIPTION,INITIALPRICE,ITEMCONDITION,LASTMODIFIED,PAYMENTMETHOD,POSTDATE,POSTAGECOST,RESERVEDPRICE,TITLE,VERSION,OID,ORDERID,SELLERID) values (10,to_timestamp('06-NOV-14 04.43.43.196000000 PM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('27-OCT-14 04.43.43.196000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'
* Intel Celeron N2840
* 2 GB DDR3L SDRAM
* 32 GB Solid-State Drive and 1TB OneDrive Cloud Storage for one year
* 11.6-Inch Screen
* Windows 8.1, 8.25-hour battery life
	 ',250,'NEW',to_timestamp('27-OCT-14 04.43.43.207000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'CA',to_timestamp('27-OCT-14 04.43.43.196000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,300,'
HP Stream 11 Laptop with Free Office 365 Personal for One Year (Horizon Blue)	
		',1,'98eccef1-6506-43bf-af39-e93e92df9d79',null,'9167164e-1a82-42cb-b66a-9a01f933cd27');
REM INSERTING into CDMR.ITEM_CATEGORY
SET DEFINE OFF;
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('23137175-9f22-40ab-b06c-a67498575e4a','2b102c3a-b05d-4bbc-8eb4-d3c9d8579f02');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('242b3b1f-7fa5-46a7-8c84-3f2e7f9e69ce','f24ca393-c577-4443-8919-6e28331fc96e');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('76a8f19b-4249-409c-9d14-913d1b4b0371','4c535f15-752c-41fe-ab6a-4357d67b8611');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('76a8f19b-4249-409c-9d14-913d1b4b0371','5faf2a9d-502e-4a30-a93e-79a90c3810c7');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('76a8f19b-4249-409c-9d14-913d1b4b0371','b4d03f5c-ada5-42d6-8734-e4e754ed8606');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('7ba95195-28ab-4d3a-ae12-61936cc8f7f9','9a6cc926-8d36-4be1-b1fd-db06a06793d0');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('7ba95195-28ab-4d3a-ae12-61936cc8f7f9','abc73f4c-cc32-4f16-b7fa-149cf8db4655');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('7ba95195-28ab-4d3a-ae12-61936cc8f7f9','d4bc06a8-1f93-4b95-9a6b-6713a5dd61cf');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('890d7263-7f7e-4fb2-99c1-f5f1cf6b0ddf','02031c34-2984-4123-bb00-ff2b7c5b3040');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('890d7263-7f7e-4fb2-99c1-f5f1cf6b0ddf','0b6b7ed2-943f-4583-b09f-dee0a67f7824');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('890d7263-7f7e-4fb2-99c1-f5f1cf6b0ddf','140237c7-01fc-4320-aa57-ce059c406314');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('890d7263-7f7e-4fb2-99c1-f5f1cf6b0ddf','98eccef1-6506-43bf-af39-e93e92df9d79');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('890d7263-7f7e-4fb2-99c1-f5f1cf6b0ddf','ca0e06d9-9ec8-4bb8-a151-0cafb95e9af1');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('8b50cea8-bf4c-4ad8-8027-43824be74280','c0e642b7-676e-4faf-8762-990131413838');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('8b50cea8-bf4c-4ad8-8027-43824be74280','e6eb5d3f-9f7c-4fc9-82bc-0dcf26e3095c');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('a2d22521-77fe-4c7c-a06f-2d8e75653b71','a28806f2-db3b-426c-825d-08043bd3be1a');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('b47d87fc-c4c9-4cea-abbe-d70f98036893','b3d51779-0429-48a3-9095-6641b132d17b');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('c5900e0d-ed24-458e-a6f8-8889d44a4596','137e99f0-4ef5-4646-b86a-1cf7f6b87774');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('c5900e0d-ed24-458e-a6f8-8889d44a4596','e6e2dc72-c9f5-4167-a22b-53a74294d183');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('c8077878-13de-4a41-94fb-ad63a44c2368','5b455439-c3f4-4449-89ab-6c032b7af0e4');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('c8077878-13de-4a41-94fb-ad63a44c2368','5c77e0c3-e99c-4f4f-bec3-1e47af2d9165');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('d37cfbff-e1ac-41ec-a8b9-c2e12a65d526','8d194146-f80d-4121-bcb2-758924e54b53');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('d905dce0-6732-4f0b-b7ed-3ab263d071d1','29bb730c-5ba1-4f0e-bdbd-9b4134965cff');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('d905dce0-6732-4f0b-b7ed-3ab263d071d1','52e716cf-f5e1-48fe-aca0-5e55bf8f21ea');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('d9fd2ed1-187b-42d8-9a2d-46d64b84b9d8','336a21c8-3cb0-4a14-a622-b37d1440be69');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('d9fd2ed1-187b-42d8-9a2d-46d64b84b9d8','be0d464a-16b5-4134-85d7-83d7282db6ed');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('ef59bf20-1d69-4607-8340-df359c63e092','8d194146-f80d-4121-bcb2-758924e54b53');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('efc80749-59f3-4b99-96e8-1c1a1583dae2','0ec1d9d7-1288-4aa8-9084-6c7064c56858');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('efc80749-59f3-4b99-96e8-1c1a1583dae2','995761de-282f-4b36-9c02-8062c186a6b3');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('efc80749-59f3-4b99-96e8-1c1a1583dae2','b4028bc0-7d1f-44b0-a6d2-baa3c6882d1b');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('f1644308-acd3-4917-8cc5-1761795a8059','05fca330-5289-4c45-b488-41ad09757d95');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('f1644308-acd3-4917-8cc5-1761795a8059','2127e20c-614e-41c1-88db-a0a130add290');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('f1644308-acd3-4917-8cc5-1761795a8059','2f81a389-fa21-4908-8eb5-b5ba19df7c73');
Insert into CDMR.ITEM_CATEGORY (CATEGORYID,ITEMID) values ('f1644308-acd3-4917-8cc5-1761795a8059','e938a454-10d3-43ae-bfdd-ebe362357b02');
REM INSERTING into CDMR.SELLER
SET DEFINE OFF;
Insert into CDMR.SELLER (OID,CREDITWORTH) values ('aa1194cc-1c1b-4ecd-b5fb-3210c827945e',0);
Insert into CDMR.SELLER (OID,CREDITWORTH) values ('f4c4f14d-1d34-4026-844e-ea3bed77aac8',0);
Insert into CDMR.SELLER (OID,CREDITWORTH) values ('39bc10f5-dac2-479b-bc97-2a09a18fd208',0);
Insert into CDMR.SELLER (OID,CREDITWORTH) values ('30a0af8f-3682-4cf6-bea3-e68e345dfa81',0);
Insert into CDMR.SELLER (OID,CREDITWORTH) values ('9167164e-1a82-42cb-b66a-9a01f933cd27',68.7);
Insert into CDMR.SELLER (OID,CREDITWORTH) values ('a4fd0e7e-0cee-44d3-9e33-b67622026de7',68.7);
REM INSERTING into CDMR.USERS
SET DEFINE OFF;
Insert into CDMR.USERS (USERTYPE,OID,CREATIONDATE,EMAIL,FISRTNAME,LASTMODIFIED,LASTNAME,PASSWORD,PHONE,USERNAME,VERSION,CITY,COUNTRY,STATE,STREELINE2,STREETLINE1,ZIPCODE,BILLINGINFOID) values ('S','aa1194cc-1c1b-4ecd-b5fb-3210c827945e',to_timestamp('10-OCT-14 09.53.32.601000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'z@z.com','z',to_timestamp('10-OCT-14 03.40.09.029000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'z','z','0','z@z.com',3,null,null,null,null,null,null,null);
Insert into CDMR.USERS (USERTYPE,OID,CREATIONDATE,EMAIL,FISRTNAME,LASTMODIFIED,LASTNAME,PASSWORD,PHONE,USERNAME,VERSION,CITY,COUNTRY,STATE,STREELINE2,STREETLINE1,ZIPCODE,BILLINGINFOID) values ('S','f4c4f14d-1d34-4026-844e-ea3bed77aac8',to_timestamp('10-OCT-14 10.18.55.938000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'d@d.com','d',to_timestamp('10-OCT-14 03.40.09.018000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'d','d','0','d@d.com',3,null,null,null,null,null,null,null);
Insert into CDMR.USERS (USERTYPE,OID,CREATIONDATE,EMAIL,FISRTNAME,LASTMODIFIED,LASTNAME,PASSWORD,PHONE,USERNAME,VERSION,CITY,COUNTRY,STATE,STREELINE2,STREETLINE1,ZIPCODE,BILLINGINFOID) values ('S','39bc10f5-dac2-479b-bc97-2a09a18fd208',to_timestamp('10-OCT-14 10.19.51.223000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'b@b.com','t',to_timestamp('10-OCT-14 03.40.09.001000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'b','b','0','b@b.com',4,null,null,null,null,null,null,null);
Insert into CDMR.USERS (USERTYPE,OID,CREATIONDATE,EMAIL,FISRTNAME,LASTMODIFIED,LASTNAME,PASSWORD,PHONE,USERNAME,VERSION,CITY,COUNTRY,STATE,STREELINE2,STREETLINE1,ZIPCODE,BILLINGINFOID) values ('S','30a0af8f-3682-4cf6-bea3-e68e345dfa81',to_timestamp('09-SEP-14 03.35.15.233000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'erika.gomez@gmail.com','Erika',to_timestamp('10-OCT-14 03.40.08.924000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'Diaz','erika','9549099312','erika.gomez@gmail.com',13,null,null,null,null,null,null,null);
Insert into CDMR.USERS (USERTYPE,OID,CREATIONDATE,EMAIL,FISRTNAME,LASTMODIFIED,LASTNAME,PASSWORD,PHONE,USERNAME,VERSION,CITY,COUNTRY,STATE,STREELINE2,STREETLINE1,ZIPCODE,BILLINGINFOID) values ('B','739897f2-0345-4c2b-a446-d6be1163596c',to_timestamp('09-SEP-14 04.52.57.725000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'jimmy.angarita@gmail.com','Jimmy',to_timestamp('02-OCT-14 11.19.31.363000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'Angarita','jimmy','6419808044','jimmy.angarita@gmail.com',4,'Plantation','USA','Florida',null,'874 nw 92nd Ave','33324',null);
Insert into CDMR.USERS (USERTYPE,OID,CREATIONDATE,EMAIL,FISRTNAME,LASTMODIFIED,LASTNAME,PASSWORD,PHONE,USERNAME,VERSION,CITY,COUNTRY,STATE,STREELINE2,STREETLINE1,ZIPCODE,BILLINGINFOID) values ('S','9167164e-1a82-42cb-b66a-9a01f933cd27',to_timestamp('27-OCT-14 03.44.36.200000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'adison.rivera@gmail.com','Adison',to_timestamp('27-OCT-14 03.44.36.204000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'Rivera','adison','0','adison.rivera@gmail.com',1,null,null,null,null,null,null,null);
Insert into CDMR.USERS (USERTYPE,OID,CREATIONDATE,EMAIL,FISRTNAME,LASTMODIFIED,LASTNAME,PASSWORD,PHONE,USERNAME,VERSION,CITY,COUNTRY,STATE,STREELINE2,STREETLINE1,ZIPCODE,BILLINGINFOID) values ('S','a4fd0e7e-0cee-44d3-9e33-b67622026de7',to_timestamp('27-OCT-14 02.55.35.960000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'giovanny.pena@gmail.com','Giovanny',to_timestamp('27-OCT-14 02.55.35.966000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'Pena','gio','0','giovanny.pena@gmail.com',1,null,null,null,null,null,null,null);
REM INSERTING into CDMR.USERS_CATEGORY
SET DEFINE OFF;
REM INSERTING into CDMR.USERSPICTURE
SET DEFINE OFF;
Insert into CDMR.USERSPICTURE (OID) values ('30a0af8f-3682-4cf6-bea3-e68e345dfa81');
Insert into CDMR.USERSPICTURE (OID) values ('39bc10f5-dac2-479b-bc97-2a09a18fd208');
Insert into CDMR.USERSPICTURE (OID) values ('739897f2-0345-4c2b-a446-d6be1163596c');
Insert into CDMR.USERSPICTURE (OID) values ('9167164e-1a82-42cb-b66a-9a01f933cd27');
Insert into CDMR.USERSPICTURE (OID) values ('a4fd0e7e-0cee-44d3-9e33-b67622026de7');
Insert into CDMR.USERSPICTURE (OID) values ('aa1194cc-1c1b-4ecd-b5fb-3210c827945e');
Insert into CDMR.USERSPICTURE (OID) values ('f4c4f14d-1d34-4026-844e-ea3bed77aac8');
REM INSERTING into CDMR.BIDDER
SET DEFINE OFF;
Insert into CDMR.BIDDER (OID,BIDFRECUENCY) values ('739897f2-0345-4c2b-a446-d6be1163596c',0);
REM INSERTING into CDMR.BID
SET DEFINE OFF;
REM INSERTING into CDMR.BILLINGINFO
SET DEFINE OFF;
REM INSERTING into CDMR.ADDRESS
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C0041995
--------------------------------------------------------

  CREATE UNIQUE INDEX "CDMR"."SYS_C0041995" ON "CDMR"."CATEGORY" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Index SYS_C0041998
--------------------------------------------------------

  CREATE UNIQUE INDEX "CDMR"."SYS_C0041998" ON "CDMR"."ITEM" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Index SYS_C0042001
--------------------------------------------------------

  CREATE UNIQUE INDEX "CDMR"."SYS_C0042001" ON "CDMR"."ITEM_CATEGORY" ("CATEGORYID", "ITEMID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Index SYS_C0041999
--------------------------------------------------------

  CREATE UNIQUE INDEX "CDMR"."SYS_C0041999" ON "CDMR"."SELLER" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Index SYS_C0041991
--------------------------------------------------------

  CREATE UNIQUE INDEX "CDMR"."SYS_C0041991" ON "CDMR"."USERS" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Index SYS_C0042002
--------------------------------------------------------

  CREATE UNIQUE INDEX "CDMR"."SYS_C0042002" ON "CDMR"."USERS_CATEGORY" ("CATEGORYID", "USERID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Index SYS_C0041992
--------------------------------------------------------

  CREATE UNIQUE INDEX "CDMR"."SYS_C0041992" ON "CDMR"."USERSPICTURE" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Index SYS_C0041993
--------------------------------------------------------

  CREATE UNIQUE INDEX "CDMR"."SYS_C0041993" ON "CDMR"."BIDDER" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Index SYS_C0041986
--------------------------------------------------------

  CREATE UNIQUE INDEX "CDMR"."SYS_C0041986" ON "CDMR"."BID" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Index SYS_C0041994
--------------------------------------------------------

  CREATE UNIQUE INDEX "CDMR"."SYS_C0041994" ON "CDMR"."BILLINGINFO" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  DDL for Index SYS_C0022002
--------------------------------------------------------

  CREATE UNIQUE INDEX "CDMR"."SYS_C0022002" ON "CDMR"."ADDRESS" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "CDMR_TABLESPACE" ;
--------------------------------------------------------
--  Constraints for Table CATEGORY
--------------------------------------------------------

  ALTER TABLE "CDMR"."CATEGORY" ADD PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ITEM
--------------------------------------------------------

  ALTER TABLE "CDMR"."ITEM" ADD PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ITEM_CATEGORY
--------------------------------------------------------

  ALTER TABLE "CDMR"."ITEM_CATEGORY" ADD PRIMARY KEY ("CATEGORYID", "ITEMID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SELLER
--------------------------------------------------------

  ALTER TABLE "CDMR"."SELLER" ADD PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "CDMR"."USERS" MODIFY ("FISRTNAME" NOT NULL ENABLE);
 
  ALTER TABLE "CDMR"."USERS" MODIFY ("LASTNAME" NOT NULL ENABLE);
 
  ALTER TABLE "CDMR"."USERS" MODIFY ("PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "CDMR"."USERS" MODIFY ("USERNAME" NOT NULL ENABLE);
 
  ALTER TABLE "CDMR"."USERS" ADD PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USERS_CATEGORY
--------------------------------------------------------

  ALTER TABLE "CDMR"."USERS_CATEGORY" ADD PRIMARY KEY ("CATEGORYID", "USERID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "CDMR_TABLESPACE"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USERSPICTURE
--------------------------------------------------------

  ALTER TABLE "CDMR"."USERSPICTURE" ADD PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE"  ENABLE;
--------------------------------------------------------
--  Constraints for Table BIDDER
--------------------------------------------------------

  ALTER TABLE "CDMR"."BIDDER" ADD PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CDMR_TABLESPACE"  ENABLE;
--------------------------------------------------------
--  Constraints for Table BID
--------------------------------------------------------

  ALTER TABLE "CDMR"."BID" ADD PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "CDMR_TABLESPACE"  ENABLE;
--------------------------------------------------------
--  Constraints for Table BILLINGINFO
--------------------------------------------------------

  ALTER TABLE "CDMR"."BILLINGINFO" ADD PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "CDMR_TABLESPACE"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ADDRESS
--------------------------------------------------------

  ALTER TABLE "CDMR"."ADDRESS" ADD PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "CDMR_TABLESPACE"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CATEGORY
--------------------------------------------------------

  ALTER TABLE "CDMR"."CATEGORY" ADD CONSTRAINT "FK_CATEGORY_PARENTCATEGORYID" FOREIGN KEY ("PARENTCATEGORYID")
	  REFERENCES "CDMR"."CATEGORY" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ITEM
--------------------------------------------------------

  ALTER TABLE "CDMR"."ITEM" ADD CONSTRAINT "FK_ITEM_ORDERID" FOREIGN KEY ("ORDERID")
	  REFERENCES "CDMR"."ORDERS" ("OID") ENABLE;
 
  ALTER TABLE "CDMR"."ITEM" ADD CONSTRAINT "FK_ITEM_SELLERID" FOREIGN KEY ("SELLERID")
	  REFERENCES "CDMR"."USERS" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ITEM_CATEGORY
--------------------------------------------------------

  ALTER TABLE "CDMR"."ITEM_CATEGORY" ADD CONSTRAINT "FK_ITEM_CATEGORY_CATEGORYID" FOREIGN KEY ("CATEGORYID")
	  REFERENCES "CDMR"."CATEGORY" ("OID") ENABLE;
 
  ALTER TABLE "CDMR"."ITEM_CATEGORY" ADD CONSTRAINT "FK_ITEM_CATEGORY_ITEMID" FOREIGN KEY ("ITEMID")
	  REFERENCES "CDMR"."ITEM" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SELLER
--------------------------------------------------------

  ALTER TABLE "CDMR"."SELLER" ADD CONSTRAINT "FK_SELLER_OID" FOREIGN KEY ("OID")
	  REFERENCES "CDMR"."USERS" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "CDMR"."USERS" ADD CONSTRAINT "FK_USERS_BILLINGINFOID" FOREIGN KEY ("BILLINGINFOID")
	  REFERENCES "CDMR"."BILLINGINFO" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USERS_CATEGORY
--------------------------------------------------------

  ALTER TABLE "CDMR"."USERS_CATEGORY" ADD CONSTRAINT "FK_USERS_CATEGORY_CATEGORYID" FOREIGN KEY ("CATEGORYID")
	  REFERENCES "CDMR"."CATEGORY" ("OID") ENABLE;
 
  ALTER TABLE "CDMR"."USERS_CATEGORY" ADD CONSTRAINT "FK_USERS_CATEGORY_USERID" FOREIGN KEY ("USERID")
	  REFERENCES "CDMR"."USERS" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USERSPICTURE
--------------------------------------------------------

  ALTER TABLE "CDMR"."USERSPICTURE" ADD CONSTRAINT "FK_USERSPICTURE_OID" FOREIGN KEY ("OID")
	  REFERENCES "CDMR"."USERS" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BIDDER
--------------------------------------------------------

  ALTER TABLE "CDMR"."BIDDER" ADD CONSTRAINT "FK_BIDDER_OID" FOREIGN KEY ("OID")
	  REFERENCES "CDMR"."USERS" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BID
--------------------------------------------------------

  ALTER TABLE "CDMR"."BID" ADD CONSTRAINT "FK_BID_BIDDERID" FOREIGN KEY ("BIDDERID")
	  REFERENCES "CDMR"."USERS" ("OID") ENABLE;
 
  ALTER TABLE "CDMR"."BID" ADD CONSTRAINT "FK_BID_ITEMID" FOREIGN KEY ("ITEMID")
	  REFERENCES "CDMR"."ITEM" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ADDRESS
--------------------------------------------------------

  ALTER TABLE "CDMR"."ADDRESS" ADD CONSTRAINT "FK_ADDRESS_OBJECTID" FOREIGN KEY ("OBJECTID")
	  REFERENCES "CDMR"."CDMR_ABSTRACTTEMPORALEXTOBJ" ("OID") ENABLE;
