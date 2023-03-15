//package com.example.topic2.repository;
//
//import com.example.topic2.entity.Ingredient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class IngredientRepositoryImpl implements IngredientRepository {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public IngredientRepositoryImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public List<Ingredient> findAll() {
//        //language=sql
//        String query = "SELECT * FROM ingredient";
//
//        return jdbcTemplate.query(query, this::mapResultSetRowToIngredient);
//    }
//
//    @Override
//    public Optional<Ingredient> findById(String id) {
//        List<Ingredient> results = jdbcTemplate.query(
//                "select id, name, type from ingredient where id=?",
//                this::mapResultSetRowToIngredient,
//                id);
//        return results.isEmpty() ?
//                Optional.empty() :
//                Optional.of(results.get(0));
//    }
//
//    @Override
//    public Ingredient save(Ingredient ingredient) {
//        jdbcTemplate.update(
//                "insert into Ingredient (id, name, type) values (?, ?, ?)",
//                ingredient.getId(),
//                ingredient.getName(),
//                ingredient.getType().toString());
//        return ingredient;
//    }
//
//    private Ingredient mapResultSetRowToIngredient(ResultSet row, int rowNom) throws SQLException {
//        return Ingredient.builder()
//                .id(row.getString("id"))
//                .name(row.getString("name"))
//                .type(Ingredient.Type.valueOf(row.getString("type")))
//                .build();
//    }
//}
