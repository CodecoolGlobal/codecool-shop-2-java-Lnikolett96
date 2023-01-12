package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
//        CartDao cartDatatStore = CartDaoMem.getInstance();

        //setting up a new supplier
        Supplier Japan = new Supplier("Japan", "The national Yokai trader");
        supplierDataStore.add(Japan);
        Supplier DemonTrader = new Supplier("Demon trader co.", "We catch demons for you.");
        supplierDataStore.add(DemonTrader);
        Supplier nationalCreatureAgency = new Supplier("National Creature Agency", "We catch famouse creatures and breed them.");
        supplierDataStore.add(nationalCreatureAgency);

        //setting up a new product category
        ProductCategory Japanese_demons = new ProductCategory("Japanese Demons", "Demon", "Demons from Japanese culture");
        productCategoryDataStore.add(Japanese_demons);

        ProductCategory Cursed_item = new ProductCategory("Cursed item", "Cursed item with a demon inside", "Haunting cursed items");
        productCategoryDataStore.add(Cursed_item);

        ProductCategory legendaryCreatures = new ProductCategory("Legendary Creatures", "Badass creatures", "These creatures need big habitat and hard self-control");
        productCategoryDataStore.add(legendaryCreatures);

        //setting up products and printing it
        try {
            productDataStore.add(new Product("Aka - Shita", new BigDecimal("49.9"), "USD", "Aka shita is a mysterious spirit which takes the form of a dark cloud with sharp claws, and a hairy, bestial face. The aka shita appears during the summer months, when rain and water are at their highest demand to ensure a successful growing season. They are agents of retribution, primarily known as punishers in water disputes.Some clever water thieves are never caught, and may think they ve gotten away with their crime. So this is a Japanese Storm demon. ", Japanese_demons, Japan,"product_1"));
            productDataStore.add(new Product("AnnaBelle", new BigDecimal("47.9"), "USD", "Annabelle is an allegedly haunted Raggedy Ann doll, housed in the (now closed) occult museum of the paranormal investigators Ed and Lorraine Warren. Annabelle was moved there after supposed hauntings in 1970. A character based on the doll is one of the antagonists that appear in the Conjuring Universe.", Cursed_item, DemonTrader,"product_2"));
            productDataStore.add(new Product("Kitsune", new BigDecimal("89"), "USD", "According to yōkai folklore, all foxes have the ability to shapeshift into human form. While some folktales speak of kitsune employing this ability to trick others—as foxes in folklore often do—other stories portray them as faithful guardians, friends, and lovers.", Japanese_demons, Japan,"product_3"));
            productDataStore.add(new Product("Jorogumo", new BigDecimal("99"), "USD", "It can shapeshift into a beautiful woman, so the kanji that represent its actual meaning are 女郎蜘蛛 (lit. 'woman-spider'); the kanji which are used to write it instead, 絡新婦 (lit. 'entangling newlywed woman') have a jukujikun pronunciation that is related to the meaning, but not the sound of the word. In Toriyama Sekien's Gazu Hyakki Yagyō, it is depicted as a spider woman manipulating small fire-breathing spiders.", Japanese_demons, Japan,"product_4"));
            productDataStore.add(new Product("Cursed Mirror", new BigDecimal("99"), "USD", "Myrtles Plantation in Louisiana, USA is said to be one of the most haunted places in the world. However, the most spooky item in the house is a mirror. Locals claim that the mirror is cursed and has the spirits of Sara Woodruff and her two children who were poisoned by their slave Chloe trapped inside it.     ", Cursed_item, DemonTrader,"product_5"));
            productDataStore.add(new Product("The anguished man painting", new BigDecimal("99"), "USD", "The Anguished Man is a painting created by an unknown artist.[1][2] Owner Sean Robinson claims to have inherited the painting from his grandmother, who told him that the artist who created the painting had mixed his own blood into the paint and committed suicide soon after finishing the work.The painting has been characterized as being supposedly haunted. ", Cursed_item, DemonTrader,"product_6"));
            productDataStore.add(new Product("Fenrir", new BigDecimal("120"), "USD", "In the Prose Edda, additional information is given about Fenrir, including that, due to the gods' knowledge of prophecies foretelling ...", legendaryCreatures, nationalCreatureAgency,"product_7"));
            productDataStore.add(new Product("Kraken", new BigDecimal("1000"), "USD", "The legend of the Kraken may have originated from sightings of giant squid, which may grow to 12–15 m (40–50 feet) in length. ...", legendaryCreatures, nationalCreatureAgency,"product_8"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}























