package com.cl.java.serialization.compare;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<String, Object> ();
		for(int i = 0; i < 100; i++) {
			map.put("a" + i , "ff" + i);
			//System.out.println("private String a" + i + "=\"ff" + i +"\";");
		}
		long start = System.currentTimeMillis();
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("A.txt"));
		os.writeObject(new A());
		os.close();
		System.out.println("cost:" + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		os = new ObjectOutputStream(new FileOutputStream("map.txt"));
		os.writeObject(map);
		os.close();
		System.out.println("cost:" + (System.currentTimeMillis() - start));
		
		
	}
}

class A implements Serializable {
	private String a0="ff0";
	private String a1="ff1";
	private String a2="ff2";
	private String a3="ff3";
	private String a4="ff4";
	private String a5="ff5";
	private String a6="ff6";
	private String a7="ff7";
	private String a8="ff8";
	private String a9="ff9";
	private String a10="ff10";
	private String a11="ff11";
	private String a12="ff12";
	private String a13="ff13";
	private String a14="ff14";
	private String a15="ff15";
	private String a16="ff16";
	private String a17="ff17";
	private String a18="ff18";
	private String a19="ff19";
	private String a20="ff20";
	private String a21="ff21";
	private String a22="ff22";
	private String a23="ff23";
	private String a24="ff24";
	private String a25="ff25";
	private String a26="ff26";
	private String a27="ff27";
	private String a28="ff28";
	private String a29="ff29";
	private String a30="ff30";
	private String a31="ff31";
	private String a32="ff32";
	private String a33="ff33";
	private String a34="ff34";
	private String a35="ff35";
	private String a36="ff36";
	private String a37="ff37";
	private String a38="ff38";
	private String a39="ff39";
	private String a40="ff40";
	private String a41="ff41";
	private String a42="ff42";
	private String a43="ff43";
	private String a44="ff44";
	private String a45="ff45";
	private String a46="ff46";
	private String a47="ff47";
	private String a48="ff48";
	private String a49="ff49";
	private String a50="ff50";
	private String a51="ff51";
	private String a52="ff52";
	private String a53="ff53";
	private String a54="ff54";
	private String a55="ff55";
	private String a56="ff56";
	private String a57="ff57";
	private String a58="ff58";
	private String a59="ff59";
	private String a60="ff60";
	private String a61="ff61";
	private String a62="ff62";
	private String a63="ff63";
	private String a64="ff64";
	private String a65="ff65";
	private String a66="ff66";
	private String a67="ff67";
	private String a68="ff68";
	private String a69="ff69";
	private String a70="ff70";
	private String a71="ff71";
	private String a72="ff72";
	private String a73="ff73";
	private String a74="ff74";
	private String a75="ff75";
	private String a76="ff76";
	private String a77="ff77";
	private String a78="ff78";
	private String a79="ff79";
	private String a80="ff80";
	private String a81="ff81";
	private String a82="ff82";
	private String a83="ff83";
	private String a84="ff84";
	private String a85="ff85";
	private String a86="ff86";
	private String a87="ff87";
	private String a88="ff88";
	private String a89="ff89";
	private String a90="ff90";
	private String a91="ff91";
	private String a92="ff92";
	private String a93="ff93";
	private String a94="ff94";
	private String a95="ff95";
	private String a96="ff96";
	private String a97="ff97";
	private String a98="ff98";
	private String a99="ff99";
	public String getA0() {
		return a0;
	}
	public void setA0(String a0) {
		this.a0 = a0;
	}
	public String getA1() {
		return a1;
	}
	public void setA1(String a1) {
		this.a1 = a1;
	}
	public String getA2() {
		return a2;
	}
	public void setA2(String a2) {
		this.a2 = a2;
	}
	public String getA3() {
		return a3;
	}
	public void setA3(String a3) {
		this.a3 = a3;
	}
	public String getA4() {
		return a4;
	}
	public void setA4(String a4) {
		this.a4 = a4;
	}
	public String getA5() {
		return a5;
	}
	public void setA5(String a5) {
		this.a5 = a5;
	}
	public String getA6() {
		return a6;
	}
	public void setA6(String a6) {
		this.a6 = a6;
	}
	public String getA7() {
		return a7;
	}
	public void setA7(String a7) {
		this.a7 = a7;
	}
	public String getA8() {
		return a8;
	}
	public void setA8(String a8) {
		this.a8 = a8;
	}
	public String getA9() {
		return a9;
	}
	public void setA9(String a9) {
		this.a9 = a9;
	}
	public String getA10() {
		return a10;
	}
	public void setA10(String a10) {
		this.a10 = a10;
	}
	public String getA11() {
		return a11;
	}
	public void setA11(String a11) {
		this.a11 = a11;
	}
	public String getA12() {
		return a12;
	}
	public void setA12(String a12) {
		this.a12 = a12;
	}
	public String getA13() {
		return a13;
	}
	public void setA13(String a13) {
		this.a13 = a13;
	}
	public String getA14() {
		return a14;
	}
	public void setA14(String a14) {
		this.a14 = a14;
	}
	public String getA15() {
		return a15;
	}
	public void setA15(String a15) {
		this.a15 = a15;
	}
	public String getA16() {
		return a16;
	}
	public void setA16(String a16) {
		this.a16 = a16;
	}
	public String getA17() {
		return a17;
	}
	public void setA17(String a17) {
		this.a17 = a17;
	}
	public String getA18() {
		return a18;
	}
	public void setA18(String a18) {
		this.a18 = a18;
	}
	public String getA19() {
		return a19;
	}
	public void setA19(String a19) {
		this.a19 = a19;
	}
	public String getA20() {
		return a20;
	}
	public void setA20(String a20) {
		this.a20 = a20;
	}
	public String getA21() {
		return a21;
	}
	public void setA21(String a21) {
		this.a21 = a21;
	}
	public String getA22() {
		return a22;
	}
	public void setA22(String a22) {
		this.a22 = a22;
	}
	public String getA23() {
		return a23;
	}
	public void setA23(String a23) {
		this.a23 = a23;
	}
	public String getA24() {
		return a24;
	}
	public void setA24(String a24) {
		this.a24 = a24;
	}
	public String getA25() {
		return a25;
	}
	public void setA25(String a25) {
		this.a25 = a25;
	}
	public String getA26() {
		return a26;
	}
	public void setA26(String a26) {
		this.a26 = a26;
	}
	public String getA27() {
		return a27;
	}
	public void setA27(String a27) {
		this.a27 = a27;
	}
	public String getA28() {
		return a28;
	}
	public void setA28(String a28) {
		this.a28 = a28;
	}
	public String getA29() {
		return a29;
	}
	public void setA29(String a29) {
		this.a29 = a29;
	}
	public String getA30() {
		return a30;
	}
	public void setA30(String a30) {
		this.a30 = a30;
	}
	public String getA31() {
		return a31;
	}
	public void setA31(String a31) {
		this.a31 = a31;
	}
	public String getA32() {
		return a32;
	}
	public void setA32(String a32) {
		this.a32 = a32;
	}
	public String getA33() {
		return a33;
	}
	public void setA33(String a33) {
		this.a33 = a33;
	}
	public String getA34() {
		return a34;
	}
	public void setA34(String a34) {
		this.a34 = a34;
	}
	public String getA35() {
		return a35;
	}
	public void setA35(String a35) {
		this.a35 = a35;
	}
	public String getA36() {
		return a36;
	}
	public void setA36(String a36) {
		this.a36 = a36;
	}
	public String getA37() {
		return a37;
	}
	public void setA37(String a37) {
		this.a37 = a37;
	}
	public String getA38() {
		return a38;
	}
	public void setA38(String a38) {
		this.a38 = a38;
	}
	public String getA39() {
		return a39;
	}
	public void setA39(String a39) {
		this.a39 = a39;
	}
	public String getA40() {
		return a40;
	}
	public void setA40(String a40) {
		this.a40 = a40;
	}
	public String getA41() {
		return a41;
	}
	public void setA41(String a41) {
		this.a41 = a41;
	}
	public String getA42() {
		return a42;
	}
	public void setA42(String a42) {
		this.a42 = a42;
	}
	public String getA43() {
		return a43;
	}
	public void setA43(String a43) {
		this.a43 = a43;
	}
	public String getA44() {
		return a44;
	}
	public void setA44(String a44) {
		this.a44 = a44;
	}
	public String getA45() {
		return a45;
	}
	public void setA45(String a45) {
		this.a45 = a45;
	}
	public String getA46() {
		return a46;
	}
	public void setA46(String a46) {
		this.a46 = a46;
	}
	public String getA47() {
		return a47;
	}
	public void setA47(String a47) {
		this.a47 = a47;
	}
	public String getA48() {
		return a48;
	}
	public void setA48(String a48) {
		this.a48 = a48;
	}
	public String getA49() {
		return a49;
	}
	public void setA49(String a49) {
		this.a49 = a49;
	}
	public String getA50() {
		return a50;
	}
	public void setA50(String a50) {
		this.a50 = a50;
	}
	public String getA51() {
		return a51;
	}
	public void setA51(String a51) {
		this.a51 = a51;
	}
	public String getA52() {
		return a52;
	}
	public void setA52(String a52) {
		this.a52 = a52;
	}
	public String getA53() {
		return a53;
	}
	public void setA53(String a53) {
		this.a53 = a53;
	}
	public String getA54() {
		return a54;
	}
	public void setA54(String a54) {
		this.a54 = a54;
	}
	public String getA55() {
		return a55;
	}
	public void setA55(String a55) {
		this.a55 = a55;
	}
	public String getA56() {
		return a56;
	}
	public void setA56(String a56) {
		this.a56 = a56;
	}
	public String getA57() {
		return a57;
	}
	public void setA57(String a57) {
		this.a57 = a57;
	}
	public String getA58() {
		return a58;
	}
	public void setA58(String a58) {
		this.a58 = a58;
	}
	public String getA59() {
		return a59;
	}
	public void setA59(String a59) {
		this.a59 = a59;
	}
	public String getA60() {
		return a60;
	}
	public void setA60(String a60) {
		this.a60 = a60;
	}
	public String getA61() {
		return a61;
	}
	public void setA61(String a61) {
		this.a61 = a61;
	}
	public String getA62() {
		return a62;
	}
	public void setA62(String a62) {
		this.a62 = a62;
	}
	public String getA63() {
		return a63;
	}
	public void setA63(String a63) {
		this.a63 = a63;
	}
	public String getA64() {
		return a64;
	}
	public void setA64(String a64) {
		this.a64 = a64;
	}
	public String getA65() {
		return a65;
	}
	public void setA65(String a65) {
		this.a65 = a65;
	}
	public String getA66() {
		return a66;
	}
	public void setA66(String a66) {
		this.a66 = a66;
	}
	public String getA67() {
		return a67;
	}
	public void setA67(String a67) {
		this.a67 = a67;
	}
	public String getA68() {
		return a68;
	}
	public void setA68(String a68) {
		this.a68 = a68;
	}
	public String getA69() {
		return a69;
	}
	public void setA69(String a69) {
		this.a69 = a69;
	}
	public String getA70() {
		return a70;
	}
	public void setA70(String a70) {
		this.a70 = a70;
	}
	public String getA71() {
		return a71;
	}
	public void setA71(String a71) {
		this.a71 = a71;
	}
	public String getA72() {
		return a72;
	}
	public void setA72(String a72) {
		this.a72 = a72;
	}
	public String getA73() {
		return a73;
	}
	public void setA73(String a73) {
		this.a73 = a73;
	}
	public String getA74() {
		return a74;
	}
	public void setA74(String a74) {
		this.a74 = a74;
	}
	public String getA75() {
		return a75;
	}
	public void setA75(String a75) {
		this.a75 = a75;
	}
	public String getA76() {
		return a76;
	}
	public void setA76(String a76) {
		this.a76 = a76;
	}
	public String getA77() {
		return a77;
	}
	public void setA77(String a77) {
		this.a77 = a77;
	}
	public String getA78() {
		return a78;
	}
	public void setA78(String a78) {
		this.a78 = a78;
	}
	public String getA79() {
		return a79;
	}
	public void setA79(String a79) {
		this.a79 = a79;
	}
	public String getA80() {
		return a80;
	}
	public void setA80(String a80) {
		this.a80 = a80;
	}
	public String getA81() {
		return a81;
	}
	public void setA81(String a81) {
		this.a81 = a81;
	}
	public String getA82() {
		return a82;
	}
	public void setA82(String a82) {
		this.a82 = a82;
	}
	public String getA83() {
		return a83;
	}
	public void setA83(String a83) {
		this.a83 = a83;
	}
	public String getA84() {
		return a84;
	}
	public void setA84(String a84) {
		this.a84 = a84;
	}
	public String getA85() {
		return a85;
	}
	public void setA85(String a85) {
		this.a85 = a85;
	}
	public String getA86() {
		return a86;
	}
	public void setA86(String a86) {
		this.a86 = a86;
	}
	public String getA87() {
		return a87;
	}
	public void setA87(String a87) {
		this.a87 = a87;
	}
	public String getA88() {
		return a88;
	}
	public void setA88(String a88) {
		this.a88 = a88;
	}
	public String getA89() {
		return a89;
	}
	public void setA89(String a89) {
		this.a89 = a89;
	}
	public String getA90() {
		return a90;
	}
	public void setA90(String a90) {
		this.a90 = a90;
	}
	public String getA91() {
		return a91;
	}
	public void setA91(String a91) {
		this.a91 = a91;
	}
	public String getA92() {
		return a92;
	}
	public void setA92(String a92) {
		this.a92 = a92;
	}
	public String getA93() {
		return a93;
	}
	public void setA93(String a93) {
		this.a93 = a93;
	}
	public String getA94() {
		return a94;
	}
	public void setA94(String a94) {
		this.a94 = a94;
	}
	public String getA95() {
		return a95;
	}
	public void setA95(String a95) {
		this.a95 = a95;
	}
	public String getA96() {
		return a96;
	}
	public void setA96(String a96) {
		this.a96 = a96;
	}
	public String getA97() {
		return a97;
	}
	public void setA97(String a97) {
		this.a97 = a97;
	}
	public String getA98() {
		return a98;
	}
	public void setA98(String a98) {
		this.a98 = a98;
	}
	public String getA99() {
		return a99;
	}
	public void setA99(String a99) {
		this.a99 = a99;
	}

	
	
}
