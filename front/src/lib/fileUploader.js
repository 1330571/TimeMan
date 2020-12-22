import http from 'axios'

class UploadFilesService {
  async upload (file, user, onUploadProgress) {
    const formData = new FormData()

    formData.append('file', file)
    formData.append('user', user)
    return http.post('http://localhost:8081/file/pic', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      },
      onUploadProgress
    })
  }

  // @Deprecated
  getFiles () {
    return http.get('')
  }
}

export default new UploadFilesService()
