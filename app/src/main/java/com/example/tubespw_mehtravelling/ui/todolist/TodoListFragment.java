//package com.example.tubespw_mehtravelling.ui.todolist;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.eras.UGD_PersistentData_Y_XXXX_130.Adapter.TodoAdapter;
//import com.eras.UGD_PersistentData_Y_XXXX_130.Database.DatabaseClient;
//import com.eras.UGD_PersistentData_Y_XXXX_130.Model.Todo;
//import com.example.tubespw_mehtravelling.Preferences.UserPreferences;
//import com.example.tubespw_mehtravelling.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TodoListFragment extends Fragment {
//    private EditText edt_todo;
//    private Button btnReset,btnAdd;
//    private RecyclerView rv_todoList;
//    private UserPreferences userPreferences;
//
//    private List<Todo> todoList;
//    private TodoAdapter todoAdapter;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View root =  inflater.inflate(R.layout.fragment_todo_list, container, false);
//        edt_todo = root.findViewById(R.id.edt_todo);
//        btnReset = root.findViewById(R.id.btnReset);
//        btnAdd = root.findViewById(R.id.btnAdd);
//        rv_todoList = root.findViewById(R.id.rv_todoList);
//
//        userPreferences = new UserPreferences(getContext());
//
//        rv_todoList.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!edt_todo.getText().toString().isEmpty()) {
//                    addTodo();
//                }else {
//                    Toast.makeText(getContext(), "Belum diisi tuh", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                edt_todo.setText("");
//            }
//        });
//
//        getTodos();
//
//        todoList = new ArrayList<>();
//
//        return root;
//    }
//
//    private void addTodo(){
//        final String title = edt_todo.getText().toString();
//
//        class AddTodo extends AsyncTask<Void, Void, Void> {
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//                Todo todo = new Todo();
//                todo.setTitle(title);
//                todo.setUser_id(userPreferences.getUserLogin().getId());
//
//                DatabaseClient.getInstance(getContext())
//                        .getDatabase()
//                        .todoDao()
//                        .insertTodo(todo);
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void unused) {
//                super.onPostExecute(unused);
//                Toast.makeText(getContext(), "Berhasil menambahkan data", Toast.LENGTH_SHORT).show();
//                edt_todo.setText("");
//                getTodos();
//            }
//
//        }
//        AddTodo addTodo = new AddTodo(  );
//        addTodo.execute();
//    }
//
//    private void getTodos() {
//        class GetTodos extends AsyncTask<Void, Void, List<Todo>> {
//
//            @Override
//            protected List<Todo> doInBackground(Void... voids) {
//                List<Todo> todoList = DatabaseClient.getInstance(getContext())
//                        .getDatabase()
//                        .todoDao()
//                        .getTodosByUserId(userPreferences.getUserLogin().getId());
//                return todoList;
//            }
//
//            @Override
//            protected void onPostExecute(List<Todo> todos) {
//                super.onPostExecute(todos);
//                todoAdapter = new TodoAdapter(todos, getContext());
//                rv_todoList.setAdapter(todoAdapter);
//            }
//        }
//
//        GetTodos getTodos = new GetTodos();
//        getTodos.execute();
//    }
//
//}