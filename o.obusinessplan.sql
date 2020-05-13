/*
 * �Զ�����ְԱ�ı�ʶ
 * */
DROP TRIGGER IF EXISTS opForInsertEmp;
DELIMITER &
CREATE TRIGGER opForInsertEmp AFTER INSERT ON employee FOR EACH ROW
BEGIN
DECLARE tree VARCHAR(20);
DECLARE i INT;
SELECT treeIds INTO tree FROM department WHERE did=new.departmentId;
UPDATE department SET `count`=`count`+1 WHERE FIND_IN_SET(did,treeIds);
SET i = 1000+new.eid;
UPDATE employee SET ecode=CONCAT('OB',i) WHERE eid=new.eid;
END &
DELIMITER ;

/**
 * �½ڲ���Ĵ�����
 * Ŀ�����޸Ĺ����ķ־��¼�ĺϼ��½������������޸Ĺ�����С˵��¼�ĺϼ��������޸�С˵��״̬Ϊ������,����С˵�������¸���Ϊ����
 */
DROP TRIGGER IF EXISTS alterForInsertSection;
DELIMITER &
CREATE TRIGGER alterForInsertSection AFTER INSERT ON section FOR EACH ROW
BEGIN
UPDATE portion SET wordsNum = wordsNum + new.wordsNum, sectionNum = sectionNum + 1 WHERE id = new.portionId;
UPDATE novel SET wordsNum = wordsNum + new.wordsNum, sectionsNum = sectionsNum + 1, lastetSectionId = new.id WHERE id = new.novelId;
UPDATE novel SET novelState = 1 WHERE id = new.novelId AND novelState != 3;
END &
DELIMITER ;

/**
 * �½ڸ��µĴ�����
 * ����½ڵ������޸��ˣ����Ӧ���·־����������С˵������������ʱ������С˵״̬
 */
DROP TRIGGER IF EXISTS alterForUpdateSection;
DELIMITER &
CREATE TRIGGER alterForUpdateSection AFTER UPDATE ON section FOR EACH ROW
BEGIN
DECLARE wordsDis INT;
SET wordsDis = new.wordsNum - old.wordsNum;
IF wordsDis != 0 THEN
UPDATE portion SET wordsNum = wordsNum + wordsDis WHERE id = new.portionId;
UPDATE novel SET wordsNum = wordsNum + wordsDis WHERE id = new.novelId;
END IF;
END &
DELIMITER ;

/**
 * �½�ɾ���Ĵ�����
 * Ŀ�����޸Ĺ����ķ־��¼�ĺϼ��½������������޸Ĺ�����С˵��¼�ĺϼ�����,����ɾ�����½�
 */
DROP TRIGGER IF EXISTS alterForDeleteSection;
DELIMITER &
CREATE TRIGGER alterForDeleteSection AFTER DELETE ON section FOR EACH ROW
BEGIN
UPDATE portion SET wordsNum = wordsNum - old.wordsNum, sectionNum = sectionNum - 1 WHERE id = old.portionId;
UPDATE novel SET wordsNum = wordsNum - old.wordsNum, sectionsNum = sectionsNum - 1 WHERE id = old.novelId;
INSERT INTO section_del(oldId,title
,content
,novelId
,portionId
,wordsNum
,creator
,createTime
,modifier
,modifyTime
,remark
,number
,lastSection
,nextSection) VALUES(old.id, old.title, old.content, old.novelId, old.portionId, old.wordsNum, old.creator, old.createTime, old.modifier, old.modifyTime, old.remark, old.number, old.lastSection, old.nextSection);
END &
DELIMITER ;