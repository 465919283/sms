package com.sms.common;

import java.util.HashMap;
import java.util.Map;

public class CommandCodeDictionary 
{
	private static final Map<CommandCode, String> commandCodeMap = new HashMap<>();
	
	static 
	{
		commandCodeMap.put(CommandCode.INTERNAL_ERROR, "ϵͳ����"); // "Unknown error"
		commandCodeMap.put(CommandCode.OK, "�����ɹ���"); //"OK"
		commandCodeMap.put(CommandCode.EMPTY_REQUEST_BODY, "������������Ϊ�ա�"); // "Empty http request body."
		commandCodeMap.put(CommandCode.USER_NOT_LOGGED_IN, "�û���û�е�¼��"); // "User has not logged in."
		commandCodeMap.put(CommandCode.NO_ACCESS_PERMISSION, "��û�иĲ���Ȩ�ޡ�"); // "User has no access permission."
		commandCodeMap.put(CommandCode.PASSWORD_HASHING_FAILED, "�����ϣʧ�ܡ�"); //"Failed to hash password"
		
		commandCodeMap.put(CommandCode.MISSING_ID, "IDΪ��"); // "Missing ID."
		commandCodeMap.put(CommandCode.SCHOOL_ID_NOT_EXIST, "�׶�԰�Ų����ڡ�"); // "Missing school ID."
		commandCodeMap.put(CommandCode.BRANCH_SCHOOL_ID_NOT_EXIST, "��԰�Ų����ڡ�"); // "Missing branch school ID."
		commandCodeMap.put(CommandCode.GROUP_ID_NOT_EXIST, "�༶�Ų����ڡ�"); // "Missing group school ID."
		
		commandCodeMap.put(CommandCode.EMPTY_USER_LOGNAME, "�û���¼������Ϊ�ա�"); // "User log name is empty."
		commandCodeMap.put(CommandCode.EMPTY_PASSWORD, "���벻��Ϊ�ա�"); // "Password is empty."
		commandCodeMap.put(CommandCode.USER_NOT_EXIST, "�û������ڡ�"); // "User does not exist."
		commandCodeMap.put(CommandCode.INCORRECT_PASSWORD, "�������"); // "Incorrect password."
		commandCodeMap.put(CommandCode.USER_WITH_SAME_LOGNAME_ALREADY_EXISTS, "�û��Ѵ��ڡ�"); // "User with same log name already exists."
		commandCodeMap.put(CommandCode.USER_WITH_SAME_NAME_ALREADY_EXISTS, "�û��Ѵ��ڡ�"); // "User with same name already exists."
		commandCodeMap.put(CommandCode.EMPTY_USER_NAME, "�û���������Ϊ�ա�"); // "User name is empty."
		commandCodeMap.put(CommandCode.UNKNOWN_ROLE_TYPE, "��Ч��ְλ���͡�"); // "Unknown role type."
		commandCodeMap.put(CommandCode.EMPTY_OLD_PASSWORD, "�����벻��Ϊ�ա�"); // "Old password is empty."
		commandCodeMap.put(CommandCode.EMPTY_NEW_PASSWORD, "�����벻��Ϊ�ա�"); // "New password is empty."
		commandCodeMap.put(CommandCode.NEW_PASSWORD_IDENTICAL_TO_OLD_PASSWORD, "���������������벻ͬ��"); // "New password is identical to old password."
		
		commandCodeMap.put(CommandCode.EMPTY_SCHOOL_NAME, "�׶�԰���Ʋ���Ϊ�ա�"); // "School name is empty."
		commandCodeMap.put(CommandCode.SCHOOL_WITH_SAME_NAME_ALREADY_EXISTS, "���׶�԰�Ѵ��ڡ�"); // "School with same name already exists."
		commandCodeMap.put(CommandCode.SCHOOL_NOT_EXIST, "���׶�԰�����ڡ�"); //"School does not exist."
		
		commandCodeMap.put(CommandCode.EMPTY_BRANCH_SCHOOL_NAME, "��԰���Ʋ���Ϊ�ա�"); // "Branch school name is empty."
		commandCodeMap.put(CommandCode.BRANCH_SCHOOL_WITH_SAME_NAME_ALREADY_EXISTS, "�÷�԰�Ѵ��ڡ�"); // "Branch school with same name already exists."
		commandCodeMap.put(CommandCode.BRANCH_SCHOOL_NOT_EXIST, "�÷�԰�����ڡ�"); //"Branch school does not exist."
		
		commandCodeMap.put(CommandCode.EMPTY_GROUP_NAME, "�༶���Ʋ���Ϊ�ա�"); // "Group name is empty."
		commandCodeMap.put(CommandCode.GROUP_WITH_SAME_NAME_ALREADY_EXISTS, "�ð༶�Ѵ��ڡ�"); // "Group with same name already exists."
		commandCodeMap.put(CommandCode.GROUP_NOT_EXIST, "�ð༶�����ڡ�"); //"Group does not exist."
		
		commandCodeMap.put(CommandCode.MONITOR_NOT_EXIST, "�ü�ز����ڡ�"); //"Monitor does not exist."
		commandCodeMap.put(CommandCode.EMPTY_MONITOR_NAME, "������Ʋ���Ϊ�ա�"); // "Monitor name is empty."
		commandCodeMap.put(CommandCode.EMPTY_CAMERA_ADDRESS, "��Ƶ��ַ����Ϊ�ա�"); // "Camera address is empty."
		commandCodeMap.put(CommandCode.MONITOR_WITH_SAME_NAME_ALREADY_EXISTS, "�ü���Ѵ��ڡ�"); // "Monitor with same name already exists."
		commandCodeMap.put(CommandCode.MONITOR_PHOTO_NOT_EXIST, "�ü���ϴ���ͼƬ�����ڡ�"); // "The uploaded monitor photo does not exist."

		commandCodeMap.put(CommandCode.EMPTY_MEMBER_NAME, "��Ա���Ʋ���Ϊ�ա�");
		commandCodeMap.put(CommandCode.MEMBER_WITH_SAME_NAME_ALREADY_EXISTS,"�û�Ա�Ѵ��ڡ�");
		commandCodeMap.put(CommandCode.MEMBER_NOT_EXIST, "�û�Ա�����ڡ�");

		commandCodeMap.put(CommandCode.EMPTY_FEE_NAME,"�ʷ����Ʋ���Ϊ�ա�");
		commandCodeMap.put(CommandCode.FEE_NOT_EXIST,"���ʷѲ����ڡ�");
		commandCodeMap.put(CommandCode.FEE_TYPE_NOT_EXIST,"���ʷ����Ͳ����ڡ�");

		commandCodeMap.put(CommandCode.INFORMATION_NOT_EXIST,"��֪ͨ�����ڡ�");
		commandCodeMap.put(CommandCode.EMPTY_UPLOAD_FILE, "�ϴ��ļ�Ϊ�ա�"); // "Upload file is empty."
	}
	
	public static String getCodeMessage(CommandCode code)
	{
		return commandCodeMap.get(code);
	}
}
