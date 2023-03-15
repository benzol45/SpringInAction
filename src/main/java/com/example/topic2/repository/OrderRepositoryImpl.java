//package com.example.topic2.repository;
//
//import com.example.topic2.entity.Ingredient;
//import com.example.topic2.entity.Taco;
//import com.example.topic2.entity.TacoOrder;
//import org.springframework.asm.Type;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcOperations;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.PreparedStatement;
//import java.sql.Statement;
//import java.sql.Types;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//@Repository
//public class OrderRepositoryImpl implements OrderRepository {
//    private final JdbcOperations jdbcOperations;
//
//    @Autowired
//    public OrderRepositoryImpl(JdbcOperations jdbcOperations) {
//        this.jdbcOperations = jdbcOperations;
//    }
//
//    @Override
//    @Transactional
//    public TacoOrder save(TacoOrder order) {
////        PreparedStatementCreatorFactory pscf =
////                new PreparedStatementCreatorFactory(
////                        "insert into Taco_Order " +
////                                "(delivery_name, delivery_street, delivery_city, "
////                                + "delivery_state, delivery_zip, cc_number, "
////                                + "cc_expiration, cc_cvv, placed_at) "
////                                + "values (?,?,?,?,?,?,?,?,?)",
////                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
////                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
////                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
////                );
////        pscf.setReturnGeneratedKeys(true);
////
////        order.setPlacedAt(new Date());
////
////        PreparedStatementCreator psc =
////                pscf.newPreparedStatementCreator(
////                        Arrays.asList(
////                                order.getDeliveryName(),
////                                order.getDeliveryStreet(),
////                                order.getDeliveryCity(),
////                                order.getDeliveryState(),
////                                order.getDeliveryZip(),
////                                order.getCcNumber(),
////                                order.getCcExpiration(),
////                                order.getCcCVV(),
////                                order.getPlacedAt()));
////        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
////        jdbcOperations.update(psc, keyHolder);
////        long orderId = (long) keyHolder.getKeys().get("id");
//
//        order.setPlacedAt(new Date());
//        //language=sql
//        String query = "INSERT INTO Taco_Order (delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) VALUES (?,?,?,?,?,?,?,?,?)";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcOperations.update(con -> {
//            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, order.getDeliveryName());
//            ps.setString(2, order.getDeliveryStreet());
//            ps.setString(3, order.getDeliveryCity());
//            ps.setString(4, order.getDeliveryState());
//            ps.setString(5, order.getDeliveryZip());
//            ps.setString(6, order.getCcNumber());
//            ps.setString(7, order.getCcExpiration());
//            ps.setString(8, order.getCcCVV());
//            ps.setDate(9,new java.sql.Date(order.getPlacedAt().getTime()));
//            return ps;
//        }, keyHolder);
//        long orderId = (long) keyHolder.getKeys().get("id");;
//
//
//        order.setId(orderId);
//        List<Taco> tacos = order.getTacos();
//        int i=0;
//        for (Taco taco : tacos) {
//            saveTaco(orderId, taco);
//        }
//        return order;
//    }
//
//    private long saveTaco(Long orderId, Taco taco) {
//        PreparedStatementCreatorFactory pscf =
//                new PreparedStatementCreatorFactory(
//                        "insert into Taco "
//                                + "(name, created_at, taco_order) "
//                                + "values (?, ?, ?)",
//                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG
//                );
//        pscf.setReturnGeneratedKeys(true);
//        PreparedStatementCreator psc =
//                pscf.newPreparedStatementCreator(
//                        Arrays.asList(
//                                taco.getName(),
//                                new Date(),
//                                orderId));
//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcOperations.update(psc, keyHolder);
//        long tacoId = (long) keyHolder.getKeys().get("id");
//        taco.setId(tacoId);
//        saveIngredientRefs(tacoId, taco.getIngredients());
//        return tacoId;
//    }
//
//    private void saveIngredientRefs(
//        long tacoId, List<Ingredient> ingredientList) {
//
//        for (Ingredient ingredient : ingredientList) {
//            jdbcOperations.update(
//                    "insert into Ingredient_Ref (ingredient, taco) "
//                            + "values (?, ?)",
//                    ingredient.getId(), tacoId);
//        }
//    }
//
//}
