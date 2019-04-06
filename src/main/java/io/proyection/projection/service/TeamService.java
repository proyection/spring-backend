package io.proyection.projection.service;

import io.proyection.projection.domain.ProjectTask;
import io.proyection.projection.domain.Team;
import io.proyection.projection.domain.User;
import io.proyection.projection.repository.ProjectTaskRepository;
import io.proyection.projection.repository.TeamRepository;
import io.proyection.projection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements ITeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectTaskRepository taskRepository;

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Iterable<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team findById(Long id){
        return teamRepository.getById(id);
    }

    public void delete(Long id){
        Team team = findById(id);
        teamRepository.delete(team);
    }

    public Team addUser(Long user_id, Long team_id){

        User user = userRepository.getById(user_id);
        Team team = teamRepository.getById(team_id);

        List<User> userList = team.getUserList();
        userList.add(user);

        team.setUserList(userList);
        return teamRepository.save(team);
    }

    @Override
    public Team addTask(Long task_id, Long team_id) {
        Team team = teamRepository.getById(team_id);
        ProjectTask task = taskRepository.getById(task_id);
        List<ProjectTask> taskList = team.getProjectTaskList();
        taskList.add(task);
        team.setProjectTaskList(taskList);
        return teamRepository.save(team);
    }
}

