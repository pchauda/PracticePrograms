package com.p.pc.cracking_the_coding_interview.moderate_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Since XML is very verbose, you are given a way of encoding it where each tag gets
 * mapped to a pre-defined integer value. The language/grammar is as follows:
 * Element --> Tag Attributes END Children END
 * Attribute --> Tag Value
 * END --> 0
 * Tag --> some predefined mapping to int
 * Value --> string value
 * For example, the following XML might be converted into the compressed string below (assuming a
 * mapping of family -> 1, person ->2, firstName -> 3, lastName -> 4, state
 * -> 5).
 * <family lastName="McDowell" state="CA">
 * <person firstName="Gayle">Some Message</ person>
 * </ family>
 * Becomes:
 * 1 4 McDowell 5 CA 0 2 3 Gayle 0 Some Message 0 0
 * Write code to print the encoded version of an XML element (passed in Element and Attribute
 * objects).
 * </p>
 */
public class XMLEncoding {
    public static void main(String[] args) {
        Element xml = getXML();
        System.out.println("Original XML in basic string format:" + xml);
        String encodedString = encodedString(xml);
        System.out.println(encodedString);
    }

    private static String encodedString(Element xml) {
        StringBuilder sb = new StringBuilder();
        encodedString(xml, sb);
        return sb.toString();
    }

    private static void encodedString(Element xml, StringBuilder sb) {
        if(xml != null) {
            convert(xml.nameCode, sb);
            if(xml.attributeList != null && !xml.attributeList.isEmpty()) {
                for(Attribute atr : xml.attributeList) {
                    convert(atr.nameCode, sb);
                    convert(atr.value, sb);
                }
            }
            convert("0", sb);
            if(xml.value != null) {
                convert(xml.value, sb);
            }
            if(xml.children != null && !xml.children.isEmpty()) {
                for(Element child : xml.children) {
                    encodedString(child, sb);
                }
            }
            convert("0", sb);
        }
    }

    private static void convert(String str, StringBuilder sb) {
        if(str != null) {
            sb.append(str).append(" ");
        }
    }

    static class Element {
        String name;
        String value;
        String nameCode;
        List<Attribute> attributeList = new ArrayList<>();
        List<Element> children = new ArrayList<>();

        Element(String name, String nameCode) {
            this.name = name;
            this.nameCode = nameCode;
        }

        Element(String name, String nameCode, String value) {
            this(name, nameCode);
            this.value = value;
        }

        boolean addAttributes(Attribute att) {
            return attributeList.add(att);
        }

        boolean addChildren(Element elem) {
            return this.children.add(elem);
        }

        @Override
        public String toString() {
            return "Element{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    ", nameCode=" + nameCode +
                    ", attributeList=" + attributeList +
                    ", children=" + children +
                    '}';
        }
    }

    static class Attribute {
        String name;
        String nameCode;
        String value;
        Attribute(String name, String nameCode, String value) {
            this.name = name;
            this.nameCode = nameCode;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Attribute{" +
                    "name='" + name + '\'' +
                    ", nameCode=" + nameCode +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    static Element getXML() {
        /**
         * <family lastName="McDowell" state="CA">
         *  <person firstName="Gayle">Some Message</ person>
         * </ family>
         */
        Element root = new Element("family", "1");
        Element person = new Element("person", "2", "Some Message");

        Attribute firstName = new Attribute("firstName", "3", "Gayle");
        Attribute lastName = new Attribute("lastName", "4", "McDowell");
        Attribute state = new Attribute("state", "5", "CA");

        person.addAttributes(firstName);
        root.addAttributes(lastName);
        root.addAttributes(state);
        root.addChildren(person);
        return root;
    }
}
