package com.diccionario.diccionario.repository.impl;

import com.diccionario.diccionario.models.LessonModel;
import com.diccionario.diccionario.models.QuestionModel;
import com.diccionario.diccionario.repository.ILessonRepository;
import com.diccionario.diccionario.utils.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LessonRepositoryImpl implements ILessonRepository {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getConnection();
    }

    @Override
    public List<LessonModel> getLessons() {

        List<LessonModel> lessons = new ArrayList<>();

        LessonModel lesson = new LessonModel();

        String query = "SELECT le.id_leccion, le.descripcion_leccion, pr.id_pregunta ,pr.pregunta, pr.diccionario_palabra\n" +
                "FROM leccion as le JOIN pregunta pr on le.id_leccion = pr.leccion_id_leccion";

        try (Statement stmt = getConnection().createStatement();

             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                lesson = mapLesson(rs);

                QuestionModel q = lesson.getPreguntas().get(0);

                if( lessons.size() == 0) {

                    lessons.add(lesson);

                } else {

                    int indexLessonId = lessons.get(lessons.size() -1 ).getIdLeccion();

                    if (indexLessonId == q.getIdLeccion()){
                        lessons.get(indexLessonId -1).getPreguntas().add(q);
                    }else{
                        lessons.add(lesson);
                    }

                }

            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return lessons;
    }
    
    public LessonModel mapLesson(ResultSet rs) throws SQLException {
        LessonModel lesson = new LessonModel();
        List<QuestionModel> questions = new ArrayList<>();
        QuestionModel question = new QuestionModel();

        lesson.setIdLeccion(rs.getInt("id_leccion"));
        lesson.setTituloLeccion(rs.getString("descripcion_leccion"));
        question.setIdPregunta(rs.getInt("id_pregunta"));
        question.setIdLeccion(rs.getInt("id_leccion"));
        question.setTextoPregunta(rs.getString("pregunta"));
        question.setPalabraDiccionario(rs.getString("diccionario_palabra"));
        questions.add(question);
        lesson.setPreguntas(questions);

        return lesson;
    }
}
