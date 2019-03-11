package cn.com.zfyc;

import cn.com.zfyc.bean.ShopEntity;
import cn.com.zfyc.bean.User;

public class UserTest {

    public static void main(String[] args) {
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setAddress("红牌楼街道佳灵路27号");
        shopEntity.setArea("四川省-成都市-武侯区");
        shopEntity.setBankAddress("成都市武侯区长益路27号");
        shopEntity.setBankId("65238526845236986532385");
        shopEntity.setBankName("中国工商银行成都分行武侯支行");
        shopEntity.setBankPhone("18227592591");
        shopEntity.setBankUserName("牟坤林");
        shopEntity.setIdNum("51348963245632895623");
        shopEntity.setIdPhoto(1);
        shopEntity.setIdPhoto(2);
        shopEntity.setLicensePhoto(3);
        shopEntity.setLat("30.23");
        shopEntity.setLng("110.75");
        shopEntity.setName("mou");
        shopEntity.setPermitPhoto(5);
        shopEntity.setShopName("三只松鼠");

        System.out.println( shopEntity.toString());

    }
}
