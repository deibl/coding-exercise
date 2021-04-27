--liquibase formatted sql
--changeset eibldavid:1 splitStatements:true endDelimiter:;

INSERT INTO category (id, domain_id, name, parent_id)
VALUES (1, '91905d52-9406-44dd-be9e-1227644af483', 'Mode', null),
       (2, '135ff6bd-1108-4c6f-8e10-7719d4fa7e8e', 'Shirts & Tops',
        '91905d52-9406-44dd-be9e-1227644af483'),
       (3, '55caf38c-11f1-4619-a1a5-1ca1c2c70827', 'Strickware',
        '91905d52-9406-44dd-be9e-1227644af483'),
       (4, '00ea5c94-1d0a-4f50-8db5-96bfb67f0fda', 'Hosen', '91905d52-9406-44dd-be9e-1227644af483'),
       (5, 'c7a08a0f-4d0e-49a4-a9e0-1c5a09dca581', 'Kosmetik', null),
       (6, '09d2c803-8bb1-4728-8a8c-0b9924996d72', 'Gesichtspflege',
        'c7a08a0f-4d0e-49a4-a9e0-1c5a09dca581'),
       (7, '8b29630b-1e57-4fd3-ba2f-faed67b10342', 'Körperpflege',
        'c7a08a0f-4d0e-49a4-a9e0-1c5a09dca581');

INSERT INTO product (id, domain_id, name, price, category_id)
VALUES (1, 'fdf2629f-a8fc-417e-bce5-71ad094c655a', 'Chiffonbluse mit Spitze', 59.99, '135ff6bd-1108-4c6f-8e10-7719d4fa7e8e'),
       (2, '95df87e2-87cc-405d-ad45-8d5071b10004', 'Spitzenshirt mit Schößchen', 49.99, '135ff6bd-1108-4c6f-8e10-7719d4fa7e8e'),
       (3, '403d259a-eca5-4aa0-9373-04c0171eb606', 'Shirt mit Brosche', 79.99, '135ff6bd-1108-4c6f-8e10-7719d4fa7e8e'),
       (4, '4d87c51e-f608-4636-b6b5-a6182f70aee3', 'Cardigan "Modern Basic"', 29.99, '55caf38c-11f1-4619-a1a5-1ca1c2c70827'),
       (5, '0cf8f94a-8841-45a5-93f7-e8bf0fb3aecd', 'Kurzarm-Pullover "Leo"', 39.98, '55caf38c-11f1-4619-a1a5-1ca1c2c70827'),
       (6, '69c57411-7cfc-401d-89bc-a4aa0c4a1bd1', 'Pullover mit Volant', 79.99, '55caf38c-11f1-4619-a1a5-1ca1c2c70827'),
       (7, '9b265a8a-2b80-4e6a-858b-de09d7983977', 'Jeggings mit Spitzenbesatz', 69.98, '00ea5c94-1d0a-4f50-8db5-96bfb67f0fda'),
       (8, '15ee4a29-82d2-492d-a7f8-68e29187ec18', '5-Pocket-Jeans mit Druck', 44.99, '00ea5c94-1d0a-4f50-8db5-96bfb67f0fda'),
       (9, 'cd6c3e5a-9a6e-41c8-8528-a230ca993e60', 'Hose mit Blumendruck', 89.99, '00ea5c94-1d0a-4f50-8db5-96bfb67f0fda'),
       (10, '3c459516-9fe8-4f2b-a4cd-219c904c77bb', 'Getönte Tagescreme Touch of Silk LSF 15', 29.99, '09d2c803-8bb1-4728-8a8c-0b9924996d72'),
       (11, 'a91618e1-a36e-4565-963d-c39c8240500a', 'Gesichtscreme & Tagesserum', 49.98, '09d2c803-8bb1-4728-8a8c-0b9924996d72'),
       (12, '0f8a8f69-6251-4055-9a83-b5b2c9f09671', 'Feuchtigkeitscreme & Augenessenz', 49.98, '09d2c803-8bb1-4728-8a8c-0b9924996d72'),
       (13, 'a1b61d52-2863-4def-8747-03a8e4a7e2e0', 'Naturcreme Soft', 24.99, '8b29630b-1e57-4fd3-ba2f-faed67b10342'),
       (14, 'a1aedf4e-198e-42bc-8401-05f9467e5092', 'Naturcreme Soft Limited Edition', 29.99, '8b29630b-1e57-4fd3-ba2f-faed67b10342'),
       (15, '26658f41-890c-410d-82af-6b0aff950b27', 'Naturcreme Classic Papagei', 29.99, '8b29630b-1e57-4fd3-ba2f-faed67b10342');
