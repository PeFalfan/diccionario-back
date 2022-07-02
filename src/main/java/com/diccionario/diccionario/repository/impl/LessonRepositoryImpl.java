package com.diccionario.diccionario.repository.impl;

import com.diccionario.diccionario.models.*;
import com.diccionario.diccionario.repository.ILessonRepository;
import com.diccionario.diccionario.utils.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LessonRepositoryImpl implements ILessonRepository {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getConnection();
    }


    public List<UserAnswerModel> getAnswers(){

        List<UserAnswerModel> answers = new ArrayList<>();

        String query = "SELECT * FROM respuestas_alumnos";

        try (Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                answers.add(mapAnswer(rs));
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        }

        return answers;
    }

    public UserAnswerModel mapAnswer(ResultSet rs) throws SQLException{

        UserAnswerModel objAnswer = new UserAnswerModel();

        objAnswer.setIdResponse(rs.getInt("id_respuesta"));
        objAnswer.setResponse(rs.getString("respuesta"));
        objAnswer.setIdUser(rs.getInt("Usuario_id"));
        objAnswer.setIdQuestion(rs.getInt("pregunta_id_pregunta"));
        objAnswer.setIdLesson(rs.getInt("pregunta_leccion_id_leccion"));
        objAnswer.setAnswer(rs.getString("pregunta_diccionario_palabra"));

        return objAnswer;
    }

    public int lastApprovedLesson(String email){
        int response = 0;

        List<LessonModel> lessons = new ArrayList<>();

        LessonModel lesson = new LessonModel();

        String query = "SELECT le.id_leccion, le.descripcion_leccion, pr.id_pregunta ,pr.pregunta, pr.diccionario_palabra, uhl.Estado_leccion_idEstado_leccion\n" +
                "FROM usuario us JOIN usuario_has_leccion uhl on us.id = uhl.Usuario_id JOIN leccion le on uhl.leccion_id_leccion = le.id_leccion LEFT JOIN pregunta pr on le.id_leccion = pr.leccion_id_leccion\n" +
                "WHERE us.email = ?";

        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            while ( rs.next()) {
                lesson = mapLesson(rs);
                lessons.add(lesson);
            }

        } catch ( SQLException e) {
            e.printStackTrace();
        }

        for (LessonModel l: lessons) {
            if (l.getEstadoLeccion() == 2){ // state 2 is completed
                response = l.getIdLeccion();
            }
        }

        return response;
    }
    @Override
    public List<LessonModel> getLessons() {

        List<LessonModel> lessons = new ArrayList<>();

        LessonModel lesson = new LessonModel();

        String query = "SELECT le.id_leccion, le.descripcion_leccion, pr.id_pregunta ,pr.pregunta, pr.diccionario_palabra, uhl.Estado_leccion_idEstado_leccion\n" +
                "FROM usuario us JOIN usuario_has_leccion uhl on us.id = uhl.Usuario_id JOIN leccion le on uhl.leccion_id_leccion = le.id_leccion " +
                "LEFT JOIN pregunta pr on le.id_leccion = pr.leccion_id_leccion";

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

    public int addCommentary(CommentaryModel comment){
        int res = 0;
        Date today = Date.valueOf(LocalDate.now());
        String query = "INSERT INTO comentario VALUES (0, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1,comment.getCommentary());
            stmt.setInt(2,comment.getIdLesson());
            stmt.setInt(3,comment.getIdUser());
            stmt.setDate(4, today);

            res = stmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    
    public LessonModel mapLesson(ResultSet rs) throws SQLException {
        LessonModel lesson = new LessonModel();
        List<QuestionModel> questions = new ArrayList<>();
        QuestionModel question = new QuestionModel();

        lesson.setIdLeccion(rs.getInt("id_leccion"));
        lesson.setTituloLeccion(rs.getString("descripcion_leccion"));
        lesson.setEstadoLeccion(rs.getInt("Estado_leccion_idEstado_leccion"));
        question.setIdPregunta(rs.getInt("id_pregunta"));
        question.setIdLeccion(rs.getInt("id_leccion"));
        question.setTextoPregunta(rs.getString("pregunta"));
        question.setPalabraDiccionario(rs.getString("diccionario_palabra"));
        questions.add(question);
        lesson.setPreguntas(questions);

        return lesson;
    }

    public List<LessonsResume> getResumeByClient(int idUser) throws SQLException{
        List<LessonsResume> resumes = new ArrayList<>();

        String query = "SELECT * FROM usuario_has_leccion WHERE usuario_id = ?";

        try ( PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, idUser);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                resumes.add(mapResume(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return resumes;
    }

    public LessonsResume mapResume(ResultSet rs) throws SQLException {

        LessonsResume resume = new LessonsResume();

        resume.setIdLesson(rs.getInt("leccion_id_leccion"));
        resume.setUserId(rs.getInt("Usuario_id"));
        resume.setStateLesson(rs.getInt("Estado_leccion_idEstado_leccion"));

        return resume;

    }

    public int approveLesson(int userId, int lessonId){
        int response = 0;

        String query = "UPDATE usuario_has_leccion SET Estado_leccion_idEstado_leccion = 2 WHERE Usuario_id = ? AND leccion_id_leccion = ?";

        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, lessonId);

            response = stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return response;
    }

}
