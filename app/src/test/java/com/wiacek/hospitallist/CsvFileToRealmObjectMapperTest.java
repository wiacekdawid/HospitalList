package com.wiacek.hospitallist;

import com.wiacek.hospitallist.data.db.model.Organisation;
import com.wiacek.hospitallist.data.util.CsvFileToRealmObjectMapper;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class CsvFileToRealmObjectMapperTest {

    @Test
    public void transformLineFromCSVFileToOrganisation() {
        //given
        String line = "18091\tNLL17\tHospital\tHospital\tIndependent Sector\tVisible\tTrue\tCamborne Redruth Community Hospital\tBarncoose Terrace\tBarncoose\t\tRedruth\tCornwall\tTR15 3ER\t50.229415893554688\t-5.2444491386413574\tNLL\tPeninsula Community Health C.I.C\t01209 318000\t\thttp://www.peninsulacommunityhealth.co.uk/our-hospitals/camborne-redruth-community-hospital.htm\t";
        //when
        Organisation organisation = CsvFileToRealmObjectMapper.transform(line);
        //then
        Assert.assertEquals(organisation.getId(), "18091");
        Assert.assertEquals(organisation.getCode(), "NLL17");
        Assert.assertEquals(organisation.getType(), "Hospital");
        Assert.assertEquals(organisation.getSubType(), "Hospital");
        Assert.assertEquals(organisation.getSector(), "Independent Sector");
        Assert.assertEquals(organisation.getStatus(), "Visible");
        Assert.assertEquals(organisation.isPimsManaged(), true);
        Assert.assertEquals(organisation.getName(), "Camborne Redruth Community Hospital");
        Assert.assertEquals(organisation.getAddress().getAddress1(), "Barncoose Terrace");
        Assert.assertEquals(organisation.getAddress().getAddress2(), "Barncoose");
        Assert.assertEquals(organisation.getAddress().getAddress3(), "");
        Assert.assertEquals(organisation.getAddress().getCity(), "Redruth");
        Assert.assertEquals(organisation.getAddress().getCounty(), "Cornwall");
        Assert.assertEquals(organisation.getAddress().getPostCode(), "TR15 3ER");
        Assert.assertEquals(organisation.getAddress().getLatitude(), "50.229415893554688");
        Assert.assertEquals(organisation.getAddress().getLongitude(), "-5.2444491386413574");
        Assert.assertEquals(organisation.getParentODSCode(), "NLL");
        Assert.assertEquals(organisation.getParentName(), "Peninsula Community Health C.I.C");
        Assert.assertEquals(organisation.getContactData().getPhone(), "01209 318000");
        Assert.assertEquals(organisation.getContactData().getEmail(), "");
        Assert.assertEquals(organisation.getContactData().getWebsite(), "http://www.peninsulacommunityhealth.co.uk/our-hospitals/camborne-redruth-community-hospital.htm");
        Assert.assertEquals(organisation.getContactData().getFax(), "");
    }
}
