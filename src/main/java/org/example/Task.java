package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.example.entity.AddressItem;
import org.example.entity.Addresses;
import org.example.entity.Hierarchy;
import org.example.entity.HierarchyItem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Task {
    private static Task context = new Task();

    private Addresses addresses;
    private Hierarchy hierarchy;

    public static void main(String[] args) {
        if (Objects.isNull(args) || args.length != 5) {
            System.out.println("5 args required:");
            System.out.println("path to AS_ADDR_OBJ.XML, path to AS_ADM_HIERARCHY.XML, date, objectIDs and keyWord");
            System.exit(1);
        }
        context.parseAddresses(args[0]);
        context.parseHierarchy(args[1]);
        Date date = null;
        Set<Long> ids = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(args[2]);
            ids = Arrays.stream(args[3].split(","))
                    .map(String::strip)
                    .mapToLong(Long::parseLong)
                    .boxed()
                    .collect(Collectors.toSet());
        } catch (ParseException | NumberFormatException e) {
            System.out.printf("Can't parse data: %s%n", e.getMessage());
            System.exit(1);
        }
        System.out.println("===== TASK 1 =====");
        context.task1(date, ids);
        System.out.println("====== TASK 2 ======");
        context.task2(args[4]);
    }

    private void parseAddresses(String path) {
        try {
            JAXBContext jaxb = JAXBContext.newInstance(Addresses.class);
            addresses = (Addresses) jaxb.createUnmarshaller().unmarshal(new FileReader(path));
        } catch (JAXBException | FileNotFoundException e) {
            System.out.printf("Can't parse %s: %s%n", path, e.getMessage());
            System.exit(1);
        }
    }

    private void parseHierarchy(String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Hierarchy.class);
            hierarchy = (Hierarchy) jaxbContext.createUnmarshaller().unmarshal(new FileReader(path));
        } catch (JAXBException | FileNotFoundException e) {
            System.out.printf("Can't parse %s: %s%n", path, e.getMessage());
            System.exit(1);
        }
    }

    private List<Long> getHierarchy(Long objectId) {
        List<Long> result = new LinkedList<>();
        while (!objectId.equals(0L)) {
            result.add(0, objectId);
            Long finalObjectId = objectId;
            objectId = hierarchy.getItems().stream()
                    .filter(e -> e.getObjectId().equals(finalObjectId))
                    .filter(HierarchyItem::getIsActive)
                    .findFirst().orElseThrow()
                    .getParentObjId();
        }
        return result;
    }

    private void task1(Date date, Set<Long> ids) {
        Predicate<AddressItem> predicate = address -> (
                (address.getStartDate().before(date) || address.getStartDate().equals(date))
                && (address.getEndDate().after(date) || address.getEndDate().equals(date)));
        addresses.getObjects().stream()
                .filter(e -> ids.contains(e.getObjectId()))
                .filter(predicate)
                .map(e -> String.format("%d: %s %s", e.getObjectId(), e.getTypeName(), e.getName()))
                .forEach(System.out::println);
    }

    private void task2(String keyWord) {
        Map<Long, String> actual = new HashMap<>();
        for (AddressItem address : addresses.getObjects()) {
            if (address.getIsActual() && address.getIsActive()) {
                actual.put(address.getObjectId(),
                        String.format("%s %s", address.getTypeName(), address.getName()));
            }
        }
        addresses.getObjects().stream()
                .filter(e -> e.getTypeName().equalsIgnoreCase(keyWord))
                .map(AddressItem::getObjectId)
                .map(this::getHierarchy)
                .map(e -> e.stream().map(actual::get).collect(Collectors.joining(", ")))
                .sorted(String::compareTo)
                .forEach(System.out::println);
    }
}
