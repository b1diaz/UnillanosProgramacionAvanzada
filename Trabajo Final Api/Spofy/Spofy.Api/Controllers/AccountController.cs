using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using Spofy.Models;
using Spofy.ViewModels;

namespace Spofy.Api.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class AccountController : ControllerBase
    {

        private readonly UserManager<Usuario> _userManager;
        private readonly SignInManager<Usuario> _signInManager;
        private IConfiguration _config;

        public AccountController(SignInManager<Usuario> signInManager, UserManager<Usuario> userManager, IConfiguration config)
        {
            _signInManager = signInManager;
            _userManager = userManager;
            _config = config;
        }

        [HttpPost]
        public async Task<IActionResult> Register(UsuarioViewModel usuario)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var user = new Usuario { UserName = usuario.Correo, Email = usuario.Correo };
                    var result = await _userManager.CreateAsync(user, usuario.Contraseña);

                    var listaErrores = new List<IdentityError>();

                    if (result.Succeeded)
                    {
                        await _signInManager.SignInAsync(user, isPersistent: false);
                        return Ok("Usuario registrado correctamente");
                    }
                    foreach (var error in result.Errors)
                    {
                        listaErrores.Add(error);
                    }

                    return BadRequest(listaErrores);
                }
                catch (Exception ex)
                {
                    return StatusCode(500, ex);
                }

            }
            else
            {
                return BadRequest("El Modelo no es valido");
            }


        }

        [HttpPost]
        public async Task<IActionResult> Login(UsuarioViewModel usuario)
        {
            if (ModelState.IsValid)
            {
                var result = await _signInManager.PasswordSignInAsync(usuario.Correo, usuario.Contraseña, false, lockoutOnFailure: false);
                if (result.Succeeded)
                {
                    var tokenString = this.GenerateJSONWebToken();
                    return Ok(new { token = tokenString, message = "Inicio de sesion Exitoso" });
                }
                if (result.IsLockedOut)
                {
                    return StatusCode(401, new { message = "usuario Cuenta de usuario bloqueada" });
                }
                else
                {
                    return BadRequest("Intento de inicio de sesión no válido");
                }
            }
            else
            {
                return BadRequest("Intento de inicio de sesión no válido");
            }
        }

        private string GenerateJSONWebToken()
        {
            var securityKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_config["Jwt:Key"]));
            var credentials = new SigningCredentials(securityKey, SecurityAlgorithms.HmacSha256);

            var token = new JwtSecurityToken(
                issuer: _config["Jwt:Issuer"],
                audience: _config["Jwt:Issuer"],
                claims: null,
                expires: DateTime.Now.AddMinutes(120),
                signingCredentials: credentials);

            return new JwtSecurityTokenHandler().WriteToken(token);
        }

    }
}
