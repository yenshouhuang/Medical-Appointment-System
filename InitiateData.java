package com.thomas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitiateData {
    public static Map<Integer, Doctor> map = new HashMap<>();

    public static void main(String[] args) {
        readData();
    }

    public static void readData() {
        //read name, age, id, skills and skill
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String name = "";
        Integer age = null;
        Integer id = null;
        List<Skill> skills = new ArrayList<>();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("/Users/huangyanshi/IdeaProjects/Medical Appointment System/src/com/thomas/DoctorData.xml"));
            document.getDocumentElement().normalize();
            NodeList doctorList = document.getElementsByTagName("DOCTOR");
            for (int i = 0; i < doctorList.getLength(); i++) {
                NodeList doctor = doctorList.item(i).getChildNodes(); //Doc1, Doc2, Doc3...
                for (int j = 0; j < doctor.getLength(); j++) {
                    Node tag = doctor.item(j); // name, age, id, skills
                    if (tag.getNodeType() == Node.ELEMENT_NODE) {
                        if (tag.getNodeName().equals("name")) {
                            name = tag.getTextContent();
//                            System.out.println(name);
                        }
                        if (tag.getNodeName().equals("age")) {
                            age = Integer.parseInt(tag.getTextContent());
//                            System.out.println(age);
                        }
                        if (tag.getNodeName().equals("id")) {
                            id = Integer.parseInt(tag.getTextContent());
//                            System.out.println(id);
                        }
                        if (tag.getNodeName().equals("skills")) {
                            NodeList skillsList = tag.getChildNodes();
                            for (int k = 0; k < skillsList.getLength(); k++) {
                                Node skillNode = skillsList.item(k);
                                if (skillNode.getNodeType() == Node.ELEMENT_NODE) {
                                    String skill = skillNode.getTextContent();
                                    skills.add(Skill.valueOf(skill));
//                                    System.out.println(Skill.valueOf(skill));
                                }
                            }
                            map.put(id, new Doctor(name, age, id, skills));
                        }
                    }
                }
            }
        } catch (Exception e) {

//            System.out.println(e);
        }
//        System.out.println(map);
    }
}
