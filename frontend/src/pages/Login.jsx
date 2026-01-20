import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../services/api'

export default function Login() {
  const [login, setLogin] = useState('')
  const [password, setPassword] = useState('')
  const navigate = useNavigate()

  async function handleLogin(e) {
    e.preventDefault()

    try {
      const response = await api.post('/auth/login', {
        login: login,
        password: password
      })

      const token = response.data.token
      
      localStorage.setItem('kiwi_token', token)

      alert('Login feito com sucesso!')
      navigate('/games')

    } catch (error) {
      alert('Erro no login! Verifique login e senha.')
      console.error(error)
    }
  }

  return (
    <div className="h-screen flex items-center justify-center bg-zinc-900 text-white">
      <form onSubmit={handleLogin} className="bg-zinc-800 p-8 rounded-lg shadow-lg w-96 flex flex-col gap-4">
        <h1 className="text-2xl font-bold text-center text-green-400">Kiwi Game List 🥝</h1>
        
        <div className="flex flex-col gap-1">
          <label>Login</label>
          <input 
            type="text" 
            className="bg-zinc-700 p-2 rounded outline-none focus:ring-2 focus:ring-green-500"
            value={login}
            onChange={e => setLogin(e.target.value)}
          />
        </div>

        <div className="flex flex-col gap-1">
          <label>Senha</label>
          <input 
            type="password" 
            className="bg-zinc-700 p-2 rounded outline-none focus:ring-2 focus:ring-green-500"
            value={password}
            onChange={e => setPassword(e.target.value)}
          />
        </div>

        <button type="submit" className="bg-green-600 p-2 rounded font-bold hover:bg-green-500 transition-colors mt-4">
          ENTRAR
        </button>
      </form>
    </div>
  )
}