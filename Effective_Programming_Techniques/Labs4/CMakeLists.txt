cmake_minimum_required(VERSION 3.23)
project (Labs4)

set(CMAKE_CXX_STANDARD 17)

add_executable(Labs4  mainWithExercise.cpp CFileLastError.h CFileLastError.cpp CFileThrowEx.cpp CFileThrowEx.h CFileErrCode.cpp CFileErrCode.h)
