cmake_minimum_required(VERSION 3.23)
project(Labs05)

set(CMAKE_CXX_STANDARD 17)

add_executable(Labs05 main.cpp CNodeStatic.cpp CNodeStatic.h CTreeStatic.cpp CTreeStatic.h CNodeDynamic.cpp CNodeDynamic.h CTreeDynamic.cpp CTreeDynamic.h)
