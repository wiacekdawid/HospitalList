package com.wiacek.hospitallist.data.util;


import com.wiacek.hospitallist.data.db.model.Address;
import com.wiacek.hospitallist.data.db.model.ContactData;
import com.wiacek.hospitallist.data.db.model.Organisation;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class CsvFileToRealmObjectMapper {
    private static final String SPLIT_STRING = "\t";

    private static final int ORGANISATION_ID = 0;
    private static final int ORGANISATION_CODE = 1;
    private static final int ORGANISATION_TYPE = 2;
    private static final int SUB_TYPE = 3;
    private static final int SECTOR = 4;
    private static final int ORGANISATION_STATUS = 5;
    private static final int IS_PIMS_MANAGED = 6;
    private static final int ORGANISATION_NAME = 7;
    private static final int ADDRESS_1 = 8;
    private static final int ADDRESS_2 = 9;
    private static final int ADDRESS_3 = 10;
    private static final int CITY = 11;
    private static final int COUNTY = 12;
    private static final int POST_CODE = 13;
    private static final int LATITUDE = 14;
    private static final int LONGITUDE = 15;
    private static final int PARENT_ODS_CODE = 16;
    private static final int PARENT_NAME = 17;
    private static final int PHONE = 18;
    private static final int EMAIL = 19;
    private static final int WEBSITE = 20;
    private static final int FAX = 21;

    public static Organisation transform(String line) {
        String[] row = line.split(SPLIT_STRING);
        Address address = new Address(
                row[ADDRESS_1],
                row[ADDRESS_2],
                row[ADDRESS_3],
                row[CITY],
                row[COUNTY],
                row[POST_CODE],
                row[LATITUDE],
                row[LONGITUDE]);

        ContactData contactData = new ContactData(
                row.length <= PHONE ? "" : row[PHONE],
                row.length <= EMAIL ? "" : row[EMAIL],
                row.length <= WEBSITE ? "" : row[WEBSITE],
                row.length <= FAX ? "" : row[FAX]);

        Organisation organisation = new Organisation(
                row[ORGANISATION_ID],
                row[ORGANISATION_CODE],
                row[ORGANISATION_TYPE],
                row[SUB_TYPE],
                row[SECTOR],
                row[ORGANISATION_STATUS],
                row[IS_PIMS_MANAGED].equals("true"),
                row[ORGANISATION_NAME],
                row[PARENT_NAME],
                row[PARENT_ODS_CODE],
                address,
                contactData);

        return organisation;
    }
}
