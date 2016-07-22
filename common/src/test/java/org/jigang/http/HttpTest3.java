package org.jigang.http;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.DocumentException;
import org.jigang.file.xml.dom4j.Dom4jXmlUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by wujigang on 16/7/6.
 */
public class HttpTest3 {

    @Test
    public void ghrspTest() throws IOException {
//        String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><Package> <Header>  <RequestType>13</RequestType>  <SendTime>2016-07-18 17:05:31</SendTime>  <ThirdSerial>20160715144318663</ThirdSerial> </Header> <Callback>  <RefundOrder>   <OrderId>dfe733df-f676-46bc-ba8f-b7a01b076d9b</OrderId>   <RefundId>20160715144318663</RefundId>   <RefundProposalNo>9048800022121948</RefundProposalNo>   <WithdrawMoney>100000</WithdrawMoney>   <AvailableMoney>100000</AvailableMoney>   <IsSuccess>1</IsSuccess>  </RefundOrder> </Callback></Package>";
        String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?>\n" +
                "<Package>\n" +
                " <Header>\n" +
                "  <RequestType>13</RequestType>\n" +
                "  <SendTime>2016-07-19 16:05:07</SendTime>\n" +
                "  <ThirdSerial>201607191138309251030405442</ThirdSerial>\n" +
                " </Header>\n" +
                " <Callback>\n" +
                "  <RefundOrder>\n" +
                "   <OrderId>201607191135130621010311870</OrderId>\n" +
                "   <RefundId>201607191138309251030405442</RefundId>\n" +
                "   <RefundProposalNo>9048800022338318</RefundProposalNo>\n" +
                "   <WithdrawMoney>1000</WithdrawMoney>\n" +
                "   <AvailableMoney>1000</AvailableMoney>\n" +
                "   <IsSuccess>1</IsSuccess>\n" +
                "  </RefundOrder>\n" +
                " </Callback>\n" +
                "</Package>\n";

        String source = "123456" + xml;
        String sign = DigestUtils.md5Hex(source.getBytes("GBK"));
        System.out.println(sign);

//        String sign = "7882b40acaee566099d32e99d0016319";
        String url = "https://tassurance-cb.huishengqian.com/ghrsp/v1/callback?sign=" + sign;

        String result = HttpUtil.postTextXml(url, xml, "GBK");
        System.out.println(result);

    }

    @Test
    public void test() throws IOException, DocumentException {

//        String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><Package xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Header><RequestType>13</RequestType><SendTime>2013-07-10 11:35:05</SendTime><ThirdSerial>guqct060hn7091</ThirdSerial><Asyn>0</Asyn><ReturnUrl></ReturnUrl><ProductCode>GHRSP0001</ProductCode></Header><Request><RefundOrder><OrderId>201307032202010</OrderId><RefundId>201307032202019</RefundId><RefundProposalNo>88962315456456</RefundProposalNo><AvailableMoney>1000000</AvailableMoney><WithdrawMoney>1000000</WithdrawMoney><IsSuccess>1</IsSuccess><FailReason>1</FailReason></RefundOrder></Request></Package>";
//        String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><Package xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Header><RequestType>13</RequestType><SendTime>2013-07-10 11:35:05</SendTime><ThirdSerial>guqct060hn7091</ThirdSerial><Asyn>0</Asyn><ReturnUrl></ReturnUrl><ProductCode>GHRSP0001</ProductCode></Header><Request><RefundOrder><OrderId>eec0814302a9f5548bda1106a6ed14af</OrderId><RefundId>29cc74dfbe0faed64965041d8a226a56</RefundId><RefundProposalNo>xxxxxxxxxx</RefundProposalNo><AvailableMoney>1000000</AvailableMoney><WithdrawMoney>1000000</WithdrawMoney><IsSuccess>1</IsSuccess><FailReason>1</FailReason></RefundOrder></Request></Package>";
        String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?>" +
                "<Package" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
                "    <Header>" +
                "        <RequestType>13</RequestType>" +
                "        <SendTime>2013-07-10 11:35:05</SendTime>" +
                "        <ThirdSerial>guqct060hn7091</ThirdSerial>" +
                "        <Asyn>0</Asyn>" +
                "        <ReturnUrl></ReturnUrl>" +
                "        <ProductCode>GHRSP0001</ProductCode>" +
                "    </Header>" +
                "    <Callback>" +
                "        <RefundOrder>" +
                "            <OrderId>201607182001457571010498347</OrderId>\n" +
                "            <RefundId>201607182042091061030802622</RefundId>\n" +
                "            <RefundProposalNo>8828900001894508</RefundProposalNo>" +
                "            <AvailableMoney>1000000</AvailableMoney>" +
                "            <WithdrawMoney>1000000</WithdrawMoney>" +
                "            <IsSuccess>1</IsSuccess>" +
                "            <FailReason>1</FailReason>" +
                "        </RefundOrder>" +
                "    </Callback>" +
                "</Package>";
//        String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><Foo><id>1</id><name>吴集刚</name></Foo>";
//        String xml = "<xml>124</xml>";

        String source = "123456" + xml;
        String sign = DigestUtils.md5Hex(source.getBytes("GBK"));

//        String url = "http://localhost:8080/ghrsp/v1/callback?sign=" + sign;
        String url = "https://tassurance-cb.huishengqian.com/ghrsp/v1/callback?sign=" + sign;
//        String url = "http://localhost:8080/xml";


        String result = HttpUtil.postTextXml(url, xml, "GBK");
//        String result = HttpUtil.postContent(url, "text/xml;charset=gbk", xml, "gbk");
        System.out.println(result + "\n\n");
//        System.out.println(Dom4jXmlUtil.prettyPrint(result, "GBK"));
    }

    @Test
    public void test2() throws IOException, DocumentException {
//        String xml = "<Package xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Header><RequestType>01</RequestType><SendTime>2013-07-10 11:35:05</SendTime><ThirdSerial>guqcshumi01001</ThirdSerial><Asyn>0</Asyn><ReturnUrl></ReturnUrl><PageReturnUrl></PageReturnUrl><ProductCode>7101</ProductCode></Header><Request><Order><OrderId>201307107101001</OrderId><TotalPremium>1000000</TotalPremium><InsBeginDate></InsBeginDate><InsEndDate></InsEndDate><InsPeriod>10</InsPeriod><ApplyNum>1</ApplyNum><Item><SkuCode>16</SkuCode><ProductCode>7101</ProductCode><ProductName>国华金钥匙1号</ProductName><Amount xsi:nil=\"true\"/><Premium>1000000</Premium></Item></Order><ApplyInfo><Holder><HolderName>陈niu</HolderName><HolderEmail>aaa@guohualife.com</HolderEmail><HolderCardType>0</HolderCardType><HolderCardNo>310110198506165118</HolderCardNo><HolderBirthday>1985-06-16</HolderBirthday><HolderSex>0</HolderSex><HolderResidentProvince>310000</HolderResidentProvince><HolderResidentCity>310100</HolderResidentCity><HolderPhone></HolderPhone><HolderMobile>13701900770</HolderMobile><HolderAddress>金海路1000号</HolderAddress><HolderZip>201206</HolderZip><OccupationCode></OccupationCode></Holder><InsuredInfo><IsHolder>1</IsHolder><InsuredList><Insured><InsuredRelation>03</InsuredRelation><BenefitInfo><IsLegal>1</IsLegal><BenefitList/></BenefitInfo></Insured></InsuredList></InsuredInfo><OtherInfo></OtherInfo></ApplyInfo></Request></Package>";
//        String xml = "<Package xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Header><RequestType>01</RequestType><SendTime>2013-07-10 11:35:05</SendTime><ThirdSerial>guqcshumi01001</ThirdSerial><Asyn>0</Asyn><ReturnUrl></ReturnUrl><PageReturnUrl></PageReturnUrl><ProductCode>7101</ProductCode></Header><Request><Order><OrderId>201307107101001</OrderId><TotalPremium>1000000</TotalPremium><InsBeginDate></InsBeginDate><InsEndDate></InsEndDate><InsPeriod>10</InsPeriod><ApplyNum>1</ApplyNum><Item><SkuCode>16</SkuCode><ProductCode>7101</ProductCode><ProductName>国华金钥匙1号</ProductName><Amount xsi:nil=\"true\"/><Premium>1000000</Premium></Item></Order><ApplyInfo><Holder><HolderName>陈niu</HolderName><HolderEmail>aaa@guohualife.com</HolderEmail><HolderCardType>0</HolderCardType><HolderCardNo>310110198506165118</HolderCardNo><HolderBirthday>1985-06-16</HolderBirthday><HolderSex>0</HolderSex><HolderResidentProvince>310000</HolderResidentProvince><HolderResidentCity>310100</HolderResidentCity><HolderPhone></HolderPhone><HolderMobile>13701900770</HolderMobile><HolderAddress>金海路1000号</HolderAddress><HolderZip>201206</HolderZip><OccupationCode></OccupationCode></Holder><InsuredInfo><IsHolder>1</IsHolder><InsuredList><Insured><InsuredRelation>03</InsuredRelation><BenefitInfo><IsLegal>1</IsLegal><BenefitList/></BenefitInfo></Insured></InsuredList></InsuredInfo><OtherInfo></OtherInfo></ApplyInfo></Request></Package>";


        //退保费用核算
//        String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><Package xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Header><RequestType>05</RequestType><SendTime>2013-07-10 11:35:05</SendTime><ThirdSerial>guqctest057091</ThirdSerial><Asyn>0</Asyn><ReturnUrl></ReturnUrl><ProductCode>7101</ProductCode></Header><Request><RefundCharge><OrderId>8828900001891238</OrderId><PolicyNo>8828900001891238</PolicyNo><IsWithdrawAll>1</IsWithdrawAll><WithdrawMoney></WithdrawMoney><IsCancelPolicy>0</IsCancelPolicy></RefundCharge></Request></Package>";
        //退保请求
//        String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><Package xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Header><RequestType>06</RequestType><SendTime>2013-07-10 11:35:05</SendTime><ThirdSerial>guqct060hn7091</ThirdSerial><Asyn>1</Asyn><ReturnUrl></ReturnUrl><ProductCode>7101</ProductCode></Header><Request><RefundOrder><OrderId>201307032202010</OrderId><RefundId>201307032202019</RefundId><PolicyValue>1000000</PolicyValue><PolicyNo>8828900000024148</PolicyNo><AvailableMoney>1000000</AvailableMoney><IsWithdrawAll>1</IsWithdrawAll><WithdrawMoney>1000000</WithdrawMoney><IsCancelPolicy>1</IsCancelPolicy></RefundOrder></Request></Package>";
        String xml = "<Package\n" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "    <Header>\n" +
                "        <RequestType>06</RequestType>\n" +
                "        <SendTime>2016-07-14 16:55:14</SendTime>\n" +
                "        <ThirdSerial>293719873493</ThirdSerial>\n" +
                "        <Asyn>1</Asyn>\n" +
                "        <ReturnUrl>https://www.baofoo.com</ReturnUrl>\n" +
                "        <ProductCode>1000</ProductCode>\n" +
                "    </Header>\n" +
                "    <Request>\n" +
                "        <RefundOrder>\n" +
                "            <OrderId>201607182001457571010498347</OrderId>\n" +
                "            <RefundId>201607182042091061030802622</RefundId>\n" +
                "            <PolicyNo>8828900001894508</PolicyNo>\n" +
                "            <PolicyValue>100000</PolicyValue>\n" +
                "            <IsWithdrawAll>0</IsWithdrawAll>\n" +
                "            <WithdrawMoney>100000</WithdrawMoney>\n" +
                "            <AvailableMoney>100000</AvailableMoney>\n" +
                "            <IsCancelPolicy>0</IsCancelPolicy>\n" +
//                "            <refundDate>2016-07-14 00:00:00</refundDate>\n" +
                "        </RefundOrder>\n" +
                "    </Request>\n" +
                "</Package>";



        String source = "123456" + xml;

        String charset = "GBK";

        String sign = DigestUtils.md5Hex(source.getBytes(charset));

//        String action = "policyCharge";
        String action = "refund";

//        String url = "https://eservicetest.guohualife.com.cn:7006/ebiz/third/vip.action?action=searchCont&action=underwrite&sign=" + sign;
        String url = "https://eservicetest.guohualife.com.cn:7006/third/entry.do?partner=huishengqian&action=" + action + "&sign=" + sign;
//        String url = "https://eservicetest.guohualife.com.cn:7006/third/entry.do?partner=huishengqian&action=policyCharge&sign=" + sign;

        String result = HttpUtil.postTextXml(url, xml, charset);
        System.out.println(result + "\n\n");
//        System.out.println(Dom4jXmlUtil.prettyPrint(result, charset));
    }

    @Test
    public void queryPolicyValue() throws IOException {
        String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?>\n" +
                "<Package xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "  <Header>\n" +
                "    <RequestType>08</RequestType>\n" +
                "    <SendTime>2013-07-10 11:35:05</SendTime>\n" +
                "    <ThirdSerial>668503736697083</ThirdSerial>\n" +
                "    <Asyn>0</Asyn>\n" +
                "    <ReturnUrl></ReturnUrl>\n" +
                "    <PageReturnUrl></PageReturnUrl>\n" +
                "    <ProductCode>7101</ProductCode>\n" +
                "  </Header>\n" +
                "  <Request>\n" +
                "    <Order>\n" +
                "      <OrderId>201607182006373611010756144</OrderId>\n" +
                "      <PolicyNo>8828900001902758</PolicyNo>\n" +
                "    </Order>\n" +
                "  </Request>\n" +
                "</Package>";

        String sign = DigestUtils.md5Hex(("123456" + xml).getBytes("GBK"));

        String url = "https://eservicetest.guohualife.com.cn:7006/third/entry.do?partner=huishengqian&action=policyValue&sign=" + sign;

        String result = HttpUtil.postTextXml(url, xml, "GBK");
        System.out.println(result);
    }
}
