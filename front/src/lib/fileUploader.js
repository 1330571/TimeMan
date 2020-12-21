import http from 'axios'

class UploadFilesService {
  upload (file, user, onUploadProgress) {
    const formData = new FormData()

    formData.append('file', file)
    formData.append('user', 1000)
    return http.post('http://localhost:8081/file/pic', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      },
      onUploadProgress
    })
  }

  getFiles () {
    return http.get('/files')
  }
}

export default new UploadFilesService()
